package com.nvvi9.skycapitalsmobile.repositories.base

import com.nvvi9.skycapitalsmobile.data.LoginInfo
import com.nvvi9.skycapitalsmobile.data.Result


interface LoginRepository {
    suspend fun addUser(loginInfo: LoginInfo): Boolean
    suspend fun login(loginInfo: LoginInfo): Boolean
    suspend fun getUsers(): Result<List<LoginInfo>>
}