package com.nikego.skycapitals.domain

import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.domain.mappers.UserItemMapper
import com.nikego.skycapitals.repositories.base.UserRepository
import javax.inject.Inject


class BalanceUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getUserItemById(userId: Int) =
        userRepository.getUserById(userId).let {
            when (it) {
                is Result.Success -> Result.Success(UserItemMapper.map(it.data))
                is Result.Error -> it
            }
        }

    suspend fun addScore(userId: Int) =
        userRepository.addScore(userId).let {
            when (it) {
                is Result.Success -> Result.Success(UserItemMapper.map(it.data))
                is Result.Error -> it
            }
        }
}