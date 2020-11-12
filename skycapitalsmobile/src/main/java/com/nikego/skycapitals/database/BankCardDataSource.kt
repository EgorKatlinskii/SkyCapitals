package com.nikego.skycapitals.database

import com.nikego.skycapitals.data.BankCard
import com.nikego.skycapitals.data.datatype.Result
import javax.inject.Inject


class BankCardDataSource @Inject constructor(private val bankCardDao: BankCardDao) {

    suspend fun addBankCard(vararg bankCard: BankCard) =
            try {
                bankCardDao.addBankCard(*bankCard)
                true
            } catch (t: Throwable) {
                false
            }

    suspend fun updateBankCard(vararg bankCard: BankCard) =
            try {
                bankCardDao.updateBankCard(*bankCard)
                true
            } catch (t: Throwable) {
                false
            }

    suspend fun getBankCards(accountId: String) =
            try {
                bankCardDao.getBankCards(accountId).let {
                    Result.Success(it)
                }
            } catch (t: Throwable) {
                Result.Error(t)
            }

    suspend fun getBankCardById(cardId: String) =
            try {
                bankCardDao.getBankCardById(cardId).let {
                    Result.Success(it)
                }
            } catch (t: Throwable) {
                Result.Error(t)
            }
}