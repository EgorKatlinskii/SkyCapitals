package com.nikego.skycapitals.repositories.base

import com.nikego.skycapitals.data.LoginInfo
import com.nikego.skycapitals.data.Result


interface LoginRepository {
    suspend fun addUser(loginInfo: LoginInfo): Boolean
    suspend fun login(loginInfo: LoginInfo): Boolean
    suspend fun getUsers(): Result<List<LoginInfo>>
}