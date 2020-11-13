package com.nikego.skycapitals.repositories.base

import androidx.lifecycle.LiveData
import com.nikego.skycapitals.data.BankCard


interface BankCardRepository {

    suspend fun addBankCard(accountId: String, expireDate: String): Boolean
    suspend fun getBankCards(accountId: String): LiveData<List<BankCard>>
    suspend fun updateBalance(cardId: String, addedCash: Double): Boolean
}