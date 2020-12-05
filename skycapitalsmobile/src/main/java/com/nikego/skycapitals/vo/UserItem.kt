package com.nikego.skycapitals.vo


data class UserItem(
    val name: String,
    val scores: List<ScoreItem>,
    val balance: Int = scores.sumOf { it.scoreBalance }
)