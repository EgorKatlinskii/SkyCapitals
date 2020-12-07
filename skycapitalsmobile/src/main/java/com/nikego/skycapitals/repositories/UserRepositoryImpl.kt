package com.nikego.skycapitals.repositories

import com.nikego.skycapitals.data.User
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.data.skycapitalsserver.UserLogin
import com.nikego.skycapitals.database.UserDataSource
import com.nikego.skycapitals.network.SkyCapitalsDataSource
import com.nikego.skycapitals.repositories.base.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val skyCapitalsDataSource: SkyCapitalsDataSource
) : UserRepository {

    override suspend fun getUser(): Result<User> =
        userDataSource.getUser()

    override suspend fun login(userLogin: UserLogin): Result<User> = try {
        skyCapitalsDataSource.login(userLogin).also {
            when (it) {
                is Result.Success -> userDataSource.addUser(it.data)
            }
        }
    } catch (t: Throwable) {
        Result.Error(t)
    }

    override suspend fun getUserById(userId: Int): Result<User> =
        userDataSource.getSingleUserById(userId)

    override suspend fun getUsers(): Result<List<User>> =
        userDataSource.getUsers()
}