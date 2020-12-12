package com.nikego.skycapitals.data

import com.squareup.moshi.Json


data class Score @JvmOverloads constructor(
    val scoreNumber: Int,
    val currency: Currency = Currency.USD,
    @Json(name = "card") val bankCards: List<BankCard> = emptyList(),
    val scoreBalance: Int = bankCards.sumOf { it.balance }
)