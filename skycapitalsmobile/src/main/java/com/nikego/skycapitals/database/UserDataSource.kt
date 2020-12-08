package com.nikego.skycapitals.database

import com.nikego.skycapitals.data.User
import com.nikego.skycapitals.data.datatype.Result
import javax.inject.Inject


class UserDataSource @Inject constructor(private val userDao: UserDao) {

    suspend fun addUser(user: User) =
        try {
            userDao.insertUser(user)
            Result.Success(user)
        } catch (t: Throwable) {
            Result.Error(t)
        }

    suspend fun updateUser(user: User) =
        try {
            userDao.updateUser(user)
            true
        } catch (t: Throwable) {
            false
        }

    suspend fun getUser() =
        try {
            userDao.getSingleUser()?.let {
                Result.Success(it)
            } ?: Result.Error(IllegalStateException("No users in database"))
        } catch (t: Throwable) {
            Result.Error(t)
        }

    suspend fun getUsers() =
        try {
            userDao.getUsers().takeIf { it.isNotEmpty() }?.let {
                Result.Success(it)
            } ?: Result.Error(IllegalStateException("No users in database"))
        } catch (t: Throwable) {
            Result.Error(t)
        }

    fun getUserById(userId: Int) =
        userDao.getUserById(userId)

    fun getSingleUserById(userId: Int) =
        try {
            userDao.getSingleUserById(userId)?.let {
                Result.Success(it)
            } ?: Result.Error(IllegalStateException("User with id $userId not found"))
        } catch (t: Throwable) {
            Result.Error(t)
        }
}