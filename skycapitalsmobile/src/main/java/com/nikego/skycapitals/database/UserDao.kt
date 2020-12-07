package com.nikego.skycapitals.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nikego.skycapitals.data.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM User LIMIT 1")
    fun getUser(): LiveData<User>

    @Query("SELECT * FROM User")
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM User WHERE userId = :userId LIMIT 1")
    fun getUserById(userId: Int): LiveData<User>

    @Query("SELECT * FROM User WHERE userId = :userId LIMIT 1")
    fun getSingleUserById(userId: Int): User?

    @Query("SELECT * FROM User LIMIT 1")
    suspend fun getSingleUser(): User?

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM User")
    suspend fun clearUsers()
}