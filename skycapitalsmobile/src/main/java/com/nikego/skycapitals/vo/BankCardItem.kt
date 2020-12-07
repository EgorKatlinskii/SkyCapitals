package com.nikego.skycapitals.vo

import com.nikego.skycapitals.data.Currency


data class BankCardItem(
    val cardNumber: Long,
    val cardHolderName: String,
    val currency: Currency,
    val balance: Int,
)