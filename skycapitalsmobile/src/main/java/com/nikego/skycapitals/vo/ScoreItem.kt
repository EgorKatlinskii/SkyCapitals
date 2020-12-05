package com.nikego.skycapitals.vo

import com.nikego.skycapitals.data.Currency

data class ScoreItem(
    val scoreNumber:Int,
    val currency: Currency,
    val bankCards:List<BankCardItem>,
    val scoreBalance:Int = bankCards.sumOf { it.balance }
)