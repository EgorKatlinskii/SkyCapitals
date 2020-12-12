package com.nikego.skycapitals.data

import java.util.*


data class BankCard @JvmOverloads constructor(
    val numberCard: Long,
    val balance: Int,
    val password: Int,
    private val nameCard: String,
    val cardType: CardType = CardType.valueOf(nameCard.toUpperCase(Locale.ROOT))
)