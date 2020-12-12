package com.nikego.skycapitals.domain

import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.domain.mappers.UserItemMapper
import com.nikego.skycapitals.repositories.base.UserRepository
import javax.inject.Inject

class TransactionUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun getScoresByUserId(userId: Int) =
        userRepository.getUserById(userId).let {
            when (it) {
                is Result.Success -> Result.Success(UserItemMapper.map(it.data).scores)
                is Result.Error -> it
            }
        }

    suspend fun getCardsByScoreNumber(scoreNumber: Int) =
        userRepository.getUsers().let {
            when (it) {
                is Result.Success -> it.data.find {
                    it.scores?.map { it.scoreNumber }?.contains(scoreNumber) ?: false
                }?.let {
                    UserItemMapper.map(it).scores.find {
                        it.scoreNumber == scoreNumber
                    }
                }?.let {
                    Result.Success(it.bankCards)
                } ?: Result.Error(IllegalStateException("No score with $scoreNumber in database"))
                is Result.Error -> it
            }
        }

    suspend fun getScoreByNumber(scoreNumber: Int) =
        userRepository.getScoreByNumber(scoreNumber).let {
            when (it) {
                is Result.Success -> UserItemMapper.map(it.data).scores
                    .find { it.scoreNumber == scoreNumber }
                    ?.let { Result.Success(it) }
                    ?: Result.Error(IllegalStateException("no score $scoreNumber in db"))
                is Result.Error -> it
            }
        }
}