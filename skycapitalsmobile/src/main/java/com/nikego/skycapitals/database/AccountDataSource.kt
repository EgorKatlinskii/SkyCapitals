package com.nikego.skycapitals.database

import com.nikego.skycapitals.data.Account
import com.nikego.skycapitals.data.datatype.Result
import javax.inject.Inject


class AccountDataSource @Inject constructor(private val accountDao: AccountDao) {

    suspend fun addLoginInfo(account: Account) =
            try {
                account.takeUnless {
                    accountDao.getEmails().contains(it.email)
                }?.let {
                    accountDao.addAccount(it)
                    true
                } ?: false
            } catch (t: Throwable) {
                false
            }

    suspend fun getUsers() =
            try {
                accountDao.getAccounts().let {
                    Result.Success(it)
                }
            } catch (t: Throwable) {
                Result.Error(t)
            }

    suspend fun getLoginInfo(email: String) =
            try {
                accountDao.getAccount(email).let {
                    Result.Success(it)
                }
            } catch (t: Throwable) {
                Result.Error(t)
            }

    suspend fun getAccountById(accountId: String) =
            try {
                accountDao.getAccountById(accountId).let {
                    Result.Success(it)
                }
            } catch (t: Throwable) {
                Result.Error(t)
            }
}