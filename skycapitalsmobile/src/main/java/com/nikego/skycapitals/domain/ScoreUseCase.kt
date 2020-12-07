package com.nikego.skycapitals.domain

import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.domain.mappers.UserItemMapper
import com.nikego.skycapitals.repositories.base.UserRepository
import javax.inject.Inject


class ScoreUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getCardsByScoreNumber(scoreNumber: Int) =
        userRepository.getUsers().let {
            when (it) {
                is Result.Success ->
                    it.data.map { UserItemMapper.map(it) }
                        .flatMap { it.scores }
                        .find { it.scoreNumber == scoreNumber }
                        ?.let { Result.Success(it) }
                        ?: Result.Error(IllegalStateException("No score with $scoreNumber score number in database"))
                is Result.Error -> it
            }
        }
}