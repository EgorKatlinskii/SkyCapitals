package com.nvvi9.skycapitalsmobile.repositories

import com.nvvi9.skycapitalsmobile.data.LoginInfo
import com.nvvi9.skycapitalsmobile.data.Result
import com.nvvi9.skycapitalsmobile.database.LoginDataSource
import com.nvvi9.skycapitalsmobile.repositories.base.LoginRepository
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(private val loginDataSource: LoginDataSource) : LoginRepository {

    override suspend fun addUser(loginInfo: LoginInfo): Boolean =
            loginDataSource.addLoginInfo(loginInfo)

    override suspend fun login(loginInfo: LoginInfo): Boolean =
            getUsers().run {
                when (this) {
                    is Result.Success -> data.contains(loginInfo)
                    is Result.Error -> false
                }
            }

    override suspend fun getUsers(): Result<List<LoginInfo>> =
            loginDataSource.getUsers()
}