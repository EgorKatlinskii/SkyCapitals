package com.nikego.skycapitals.repositories

import com.nikego.skycapitals.data.Account
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.database.AccountDataSource
import com.nikego.skycapitals.repositories.base.LoginRepository
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(private val accountDataSource: AccountDataSource) : LoginRepository {

    override suspend fun addUser(account: Account): Boolean =
            accountDataSource.addLoginInfo(account)

    override suspend fun login(email: String, password: String): Boolean =
            accountDataSource.getLoginInfo(email).run {
                when (this) {
                    is Result.Success -> data?.password == password
                    is Result.Error -> false
                }
            }

    override suspend fun getUsers(): Result<List<Account>> =
            accountDataSource.getUsers()
}