package com.nikego.skycapitals.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nikego.skycapitals.data.BankCard


@Dao
interface BankCardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBankCard(vararg bankCard: BankCard)

    @Update
    suspend fun updateBankCard(vararg bankCard: BankCard)

    @Query("SELECT * FROM BankCard WHERE cardHolderId=:accountId")
    suspend fun getBankCardList(accountId: String): List<BankCard>

    @Query("SELECT * FROM BankCard WHERE cardHolderId=:accountId")
    suspend fun getBankCards(accountId: String): LiveData<List<BankCard>>

    @Query("SELECT * FROM BankCard WHERE cardId=:cardId")
    suspend fun getBankCardById(cardId: String): BankCard?
}