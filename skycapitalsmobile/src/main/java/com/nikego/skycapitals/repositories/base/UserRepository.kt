package com.nikego.skycapitals.repositories.base

import com.nikego.skycapitals.data.User
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.data.skycapitalsserver.UserLogin


interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUser(): Result<User>
    suspend fun login(userLogin: UserLogin): Result<User>
    suspend fun getUserById(userId: Int): Result<User>
}