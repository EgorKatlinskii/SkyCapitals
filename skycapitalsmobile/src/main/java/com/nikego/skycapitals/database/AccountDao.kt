package com.nikego.skycapitals.database

import androidx.room.*
import com.nikego.skycapitals.data.Account


@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAccount(account: Account)

    @Update
    suspend fun updateAccount(vararg: Account)

    @Query("SELECT email FROM Account")
    suspend fun getEmails(): List<String>

    @Query("SELECT * FROM Account WHERE email=:email")
    suspend fun getAccount(email: String): Account?

    @Query("SELECT * FROM Account WHERE accountId=:accountId")
    suspend fun getAccountById(accountId: String): Account?

    @Transaction
    @Query("SELECT * FROM Account")
    suspend fun getAccounts(): List<Account>
}