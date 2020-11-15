package com.nikego.skycapitals.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.nikego.skycapitals.data.AccountWithCards


@Dao
interface AccountWithCardsDao {

    @Transaction
    @Query("SELECT * FROM Account WHERE accountId=:accountId")
    suspend fun getAccountWithCards(accountId: String): AccountWithCards?

    @Transaction
    @Query("SELECT * FROM Account WHERE accountId=:accountId")
    fun getAccountWithCardsLiveData(accountId: String): LiveData<AccountWithCards>
}