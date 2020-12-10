package com.nikego.skycapitals.repositories

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
}