package com.nikego.skycapitals.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nikego.skycapitals.data.User


@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class EBankDatabase : RoomDatabase() {

    abstract val userDao: UserDao
}