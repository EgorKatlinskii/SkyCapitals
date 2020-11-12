package com.nikego.skycapitals.repositories.base

import com.nikego.skycapitals.data.Account
import com.nikego.skycapitals.data.datatype.Result


interface LoginRepository {
    suspend fun addUser(account: Account): Boolean
    suspend fun login(email: String, password: String): Boolean
    suspend fun getUsers(): Result<List<Account>>
}