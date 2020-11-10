package com.nikego.skycapitals.database

import com.nikego.skycapitals.data.LoginInfo
import com.nikego.skycapitals.data.Result
import javax.inject.Inject


class LoginDataSource @Inject constructor(private val loginInfoDao: LoginInfoDao) {

    suspend fun addLoginInfo(loginInfo: LoginInfo) =
            try {
                loginInfo.takeUnless {
                    loginInfoDao.getEmails().contains(it.email)
                }?.let {
                    loginInfoDao.addUser(it)
                    true
                } ?: false
            } catch (t: Throwable) {
                false
            }

    suspend fun getUsers() =
            try {
                loginInfoDao.getUsers().let {
                    Result.Success(it)
                }
            } catch (t: Throwable) {
                Result.Error(t)
            }

    suspend fun getLoginInfo(email: String) =
            try {
                loginInfoDao.getLoginInfo(email).let {
                    Result.Success(it)
                }
            } catch (t: Throwable) {
                Result.Error(t)
            }
}