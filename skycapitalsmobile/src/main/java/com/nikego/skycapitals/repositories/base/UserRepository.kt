package com.nikego.skycapitals.repositories.base

import com.nikego.skycapitals.data.CardType
import com.nikego.skycapitals.data.User
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.data.skycapitalsserver.UserLogin
import com.nikego.skycapitals.data.skycapitalsserver.UserRegister


interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUser(): Result<User>
    suspend fun login(userLogin: UserLogin): Result<User>
    suspend fun getUserById(userId: Int): Result<User>
    suspend fun register(userRegister: UserRegister): Result<User>
    suspend fun addScore(userId: Int): Result<User>
    suspend fun addBankCard(cardType: CardType, scoreNumber: Int): Result<User>
}