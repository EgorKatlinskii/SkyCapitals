package com.nvvi9.skycapitalsmobile.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nvvi9.skycapitalsmobile.data.LoginInfo


@Database(entities = [LoginInfo::class], version = 1, exportSchema = false)
abstract class LoginDatabase : RoomDatabase() {

    abstract val loginInfoDao: LoginInfoDao
}