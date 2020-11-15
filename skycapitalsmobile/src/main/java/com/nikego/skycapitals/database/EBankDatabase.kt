package com.nikego.skycapitals.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nikego.skycapitals.data.Account
import com.nikego.skycapitals.data.BankCard
import com.nikego.skycapitals.data.converters.CurrencyConverter


@Database(entities = [Account::class, BankCard::class], version = 1, exportSchema = false)
@TypeConverters(CurrencyConverter::class)
abstract class EBankDatabase : RoomDatabase() {

    abstract val accountDao: AccountDao
    abstract val bankCardDao: BankCardDao
    abstract val accountWithCardsDao: AccountWithCardsDao
}