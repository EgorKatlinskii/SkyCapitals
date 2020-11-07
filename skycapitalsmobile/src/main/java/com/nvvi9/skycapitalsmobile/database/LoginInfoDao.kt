package com.nvvi9.skycapitalsmobile.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nvvi9.skycapitalsmobile.data.LoginInfo


@Dao
interface LoginInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replace(loginInfo: LoginInfo)

    @Query("SELECT * FROM LoginInfo")
    suspend fun getUsers(): List<LoginInfo>
}