package com.nikego.skycapitals.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikego.skycapitals.data.LoginInfo


@Dao
interface LoginInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(loginInfo: LoginInfo)

    @Query("SELECT * FROM LoginInfo")
    suspend fun getUsers(): List<LoginInfo>
}