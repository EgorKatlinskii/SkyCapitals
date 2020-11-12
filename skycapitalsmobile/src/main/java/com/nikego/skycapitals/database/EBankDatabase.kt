package com.nikego.skycapitals.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikego.skycapitals.data.Account
import com.nikego.skycapitals.data.BankCard


@Database(entities = [Account::class, BankCard::class], version = 1, exportSchema = false)
abstract class EBankDatabase : RoomDatabase() {

    abstract val accountDao: AccountDao
    abstract val bankCardDao: BankCardDao
}