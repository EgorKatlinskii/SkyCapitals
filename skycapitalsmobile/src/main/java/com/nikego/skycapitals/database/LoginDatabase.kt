package com.nikego.skycapitals.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikego.skycapitals.data.LoginInfo


@Database(entities = [LoginInfo::class], version = 1, exportSchema = false)
abstract class LoginDatabase : RoomDatabase() {

    abstract val loginInfoDao: LoginInfoDao
}