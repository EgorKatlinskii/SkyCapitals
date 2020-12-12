package com.nikego.skycapitals.vo


data class UserItem(
    val userId: Int,
    val name: String,
    val scores: List<ScoreItem>
)