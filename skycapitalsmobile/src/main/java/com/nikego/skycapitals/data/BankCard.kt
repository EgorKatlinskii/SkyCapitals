package com.nikego.skycapitals.data


data class BankCard(
    val numberCard: Long,
    val balance: Int,
    val password: Int,
    private val nameCard: String,
    @Transient val cardType: CardType = CardType.valueOf(nameCard)
)