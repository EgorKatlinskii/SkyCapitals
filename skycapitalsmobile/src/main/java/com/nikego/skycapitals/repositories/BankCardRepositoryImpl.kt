package com.nikego.skycapitals.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.nikego.skycapitals.data.BankCard
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.database.AccountDataSource
import com.nikego.skycapitals.database.BankCardDataSource
import com.nikego.skycapitals.repositories.base.BankCardRepository
import java.util.*
import javax.inject.Inject


class BankCardRepositoryImpl @Inject constructor(
        private val accountDataSource: AccountDataSource,
        private val bankCardDataSource: BankCardDataSource
) : BankCardRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun addBankCard(accountId: String, expireDate: String): Boolean =
            accountDataSource.getAccountById(accountId).run {
                when (this) {
                    is Result.Success -> data?.run {
                        bankCardDataSource.addBankCard(
                                BankCard(
                                        UUID.randomUUID().toString(),
                                        accountId,
                                        "$lastName $firstName".toUpperCase(),
                                        Calendar.getInstance().apply {
                                            add(Calendar.YEAR, 2)
                                        }.timeInMillis
                                )
                        )
                        true
                    } ?: false
                    is Result.Error -> false
                }
            }

    override suspend fun getBankCards(accountId: String): LiveData<List<BankCard>> =
            bankCardDataSource.getBankCards(accountId)

    override suspend fun updateBalance(cardId: String, addedCash: Double): Boolean =
            bankCardDataSource.getBankCardById(cardId).run {
                when (this) {
                    is Result.Success -> data?.let {
                        bankCardDataSource.updateBankCard(it.apply {
                            balance += addedCash
                        })
                    } ?: false
                    is Result.Error -> false
                }
            }
}