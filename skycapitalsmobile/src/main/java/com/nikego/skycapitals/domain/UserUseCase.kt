package com.nikego.skycapitals.domain

import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.data.skycapitalsserver.UserLogin
import com.nikego.skycapitals.data.skycapitalsserver.UserRegister
import com.nikego.skycapitals.domain.mappers.UserItemMapper
import com.nikego.skycapitals.repositories.base.UserRepository
import javax.inject.Inject


class UserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getUserItem() =
        userRepository.getUser().let {
            when (it) {
                is Result.Success -> Result.Success(UserItemMapper.map(it.data))
                is Result.Error -> it
            }
        }

    suspend fun getUserItemById(userId: Int) =
        userRepository.getUserById(userId).let {
            when (it) {
                is Result.Success -> Result.Success(UserItemMapper.map(it.data))
                is Result.Error -> it
            }
        }

    suspend fun register(userName: String, userSurname: String, password: Int, email: String) =
        userRepository.register(UserRegister(userName, userSurname, password, email)).let {
            when (it) {
                is Result.Success -> Result.Success(it.data.userId)
                is Result.Error -> it
            }
        }

    suspend fun login(email: String, password: Int) =
        userRepository.login(UserLogin(email, password)).let {
            when (it) {
                is Result.Success -> Result.Success(it.data.userId)
                is Result.Error -> it
            }
        }
}