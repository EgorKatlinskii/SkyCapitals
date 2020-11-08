package com.nvvi9.skycapitalsmobile.database

import com.nvvi9.skycapitalsmobile.data.LoginInfo
import com.nvvi9.skycapitalsmobile.data.Result
import javax.inject.Inject


class LoginDataSource @Inject constructor(private val loginInfoDao: LoginInfoDao) {

    suspend fun addLoginInfo(loginInfo: LoginInfo) =
            try {
                loginInfoDao.addUser(loginInfo)
                true
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
}