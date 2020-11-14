package com.nikego.skycapitals.database

import com.nikego.skycapitals.data.BankCard
import com.nikego.skycapitals.data.datatype.Result
import javax.inject.Inject


class BankCardDataSource @Inject constructor(
        private val bankCardDao: BankCardDao,
        private val accountWithCardsDao: AccountWithCardsDao
) {

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

    suspend fun getBankCardList(accountId: String) =
            try {
                accountWithCardsDao.getAccountWithCards(accountId).let {
                    Result.Success(it)
                }
            } catch (t: Throwable) {
                Result.Error(t)
            }

    fun getBankCards(accountId: String) =
            accountWithCardsDao.getAccountWithCardsLiveData(accountId)

    suspend fun getBankCardById(cardId: String) =
            try {
                bankCardDao.getBankCardById(cardId).let {
                    Result.Success(it)
                }
            } catch (t: Throwable) {
                Result.Error(t)
            }
}