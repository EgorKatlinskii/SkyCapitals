package com.nikego.skycapitals.repositories

import com.nikego.skycapitals.data.LoginInfo
import com.nikego.skycapitals.data.Result
import com.nikego.skycapitals.database.LoginDataSource
import com.nikego.skycapitals.repositories.base.LoginRepository
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