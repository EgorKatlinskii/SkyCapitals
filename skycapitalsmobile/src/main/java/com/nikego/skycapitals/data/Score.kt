package com.nikego.skycapitals.data


data class Score @JvmOverloads constructor(
    val scoreNumber: Int,
    val currency: Currency = Currency.USD,
    val bankCards: List<BankCard> = emptyList(),
    val scoreBalance: Int = bankCards.sumOf { it.balance }
)