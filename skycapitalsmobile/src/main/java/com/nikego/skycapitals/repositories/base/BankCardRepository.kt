package com.nikego.skycapitals.repositories.base

import androidx.lifecycle.LiveData
import com.nikego.skycapitals.data.BankCard
import com.nikego.skycapitals.data.Currency


interface BankCardRepository {

    suspend fun addBankCard(accountId: String, currency: Currency): Boolean
    suspend fun updateBalance(cardId: String, addedCash: Double): Boolean
    fun getBankCards(accountId: String): LiveData<List<BankCard>>
}