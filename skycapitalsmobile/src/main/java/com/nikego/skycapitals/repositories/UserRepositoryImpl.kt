package com.nikego.skycapitals.repositories

import com.nikego.skycapitals.data.CardType
import com.nikego.skycapitals.data.Score
import com.nikego.skycapitals.data.User
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.data.skycapitalsserver.UserLogin
import com.nikego.skycapitals.data.skycapitalsserver.UserRegister
import com.nikego.skycapitals.database.UserDataSource
import com.nikego.skycapitals.network.SkyCapitalsDataSource
import com.nikego.skycapitals.repositories.base.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val skyCapitalsDataSource: SkyCapitalsDataSource
) : UserRepository {

    override suspend fun getUser(): Result<User> =
        userDataSource.getUser()

    override suspend fun login(userLogin: UserLogin) =
        skyCapitalsDataSource.login(userLogin).let {
            when (it) {
                is Result.Success -> userDataSource.addUser(it.data)
                is Result.Error -> it
            }
        }

    override suspend fun getUserById(userId: Int): Result<User> =
        userDataSource.getSingleUserById(userId)

    override suspend fun getUsers(): Result<List<User>> =
        userDataSource.getUsers()

    override suspend fun register(userRegister: UserRegister) =
        skyCapitalsDataSource.register(userRegister).let {
            when (it) {
                is Result.Success -> it.let { userDataSource.addUser(it.data) }
                is Result.Error -> it
            }
        }

    override suspend fun addScore(userId: Int): Result<User> =
        userDataSource.getSingleUserById(userId).let { resultUser ->
            when (resultUser) {
                is Result.Success -> resultUser.data.let { user ->
                    skyCapitalsDataSource.addScore(user.email).let { resultScoreCreate ->
                        when (resultScoreCreate) {
                            is Result.Success -> userDataSource.addUser(
                                user.copy(scores = user.scores + Score(resultScoreCreate.data.numberScore))
                            )
                            is Result.Error -> resultScoreCreate
                        }
                    }
                }
                is Result.Error -> resultUser
            }
        }

    override suspend fun addBankCard(cardType: CardType, scoreNumber: Int): Result<User> =
        userDataSource.getUsers().let { resultUsers ->
            when (resultUsers) {
                is Result.Success -> resultUsers.data.find {
                    it.scores.map { it.scoreNumber }.contains(scoreNumber)
                }?.let { user ->
                    user.scores.find {
                        it.scoreNumber == scoreNumber
                    }?.let { score ->
                        skyCapitalsDataSource.addBankCard(cardType, scoreNumber)
                            .let { resultBankCard ->
                                when (resultBankCard) {
                                    is Result.Success -> user.scores.toMutableList().apply {
                                        remove(score)
                                        add(score.copy(bankCards = score.bankCards + resultBankCard.data))
                                    }.toList().let {
                                        userDataSource.addUser(user.copy(scores = it))
                                    }
                                    is Result.Error -> resultBankCard
                                }
                            }
                    }
                } ?: Result.Error(IllegalStateException("No score with number $scoreNumber in db"))
                is Result.Error -> resultUsers
            }
        }

    override suspend fun getScoreByNumber(scoreNumber: Int): Result<User> =
        userDataSource.getUsers().let { resultUsers ->
            when (resultUsers) {
                is Result.Success -> resultUsers.data.find {
                    it.scores.map { it.scoreNumber }.contains(scoreNumber)
                }?.let {
                    Result.Success(it)
                }
                    ?: Result.Error(java.lang.IllegalStateException("no user with score $scoreNumber in db"))
                is Result.Error -> resultUsers
            }
        }

    override suspend fun getUserByCardNumber(cardNumber: Long): Result<User> =
        userDataSource.getUsers().let { resultUsers ->
            when (resultUsers) {
                is Result.Success -> resultUsers.data.find {
                    it.scores.map { it.bankCards.map { it.numberCard } }.flatten()
                        .contains(cardNumber)
                }?.let {
                    Result.Success(it)
                }
                    ?: Result.Error(java.lang.IllegalStateException("no user with card $cardNumber in db"))
                is Result.Error -> resultUsers
            }
        }

    override suspend fun sendTransaction(
        cardNumber: Long,
        receiveCardNumber: Long,
        sum: Int
    ): Result<User> =
        skyCapitalsDataSource.sendTransaction(cardNumber, receiveCardNumber, sum).let {
            when (it) {
                is Result.Success -> it.data.status.takeIf {
                    it == "Операция успешно выполнена!"
                }?.let {
                    getUserByCardNumber(cardNumber).let { resultUser ->
                        when (resultUser) {
                            is Result.Success -> {
                                resultUser.data.scores.find {
                                    it.bankCards.map { it.numberCard }.contains(cardNumber)
                                }?.let { oldScore ->
                                    oldScore.copy(
                                        bankCards = oldScore.bankCards.toMutableList().apply {
                                            find { it.numberCard == cardNumber }?.let {
                                                remove(it)
                                                add(it.copy(balance = it.balance - sum))
                                            }
                                        }.toList()
                                    ).let {
                                        resultUser.data.copy(
                                            scores = resultUser.data.scores.toMutableList().apply {
                                                remove(oldScore)
                                                add(it)
                                            }
                                        ).let {
                                            userDataSource.addUser(it)
                                        }
                                    }
                                }
                            }
                            is Result.Error -> resultUser
                        }
                    }
                }
                    ?: Result.Error(java.lang.IllegalStateException("no user with card $cardNumber in db"))
                is Result.Error -> it
            }
        }
}