package com.nikego.skycapitals.repositories.base

import com.nikego.skycapitals.data.BankCard
import com.nikego.skycapitals.data.datatype.Result


interface BankCardRepository {

    suspend fun addBankCard(accountId: String, expireDate: String): Boolean
    suspend fun getBankCards(accountId: String): Result<List<BankCard>>
    suspend fun updateBalance(cardId: String, addedCash: Double): Boolean
}