package com.nikego.skycapitals.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity
data class User(
    @PrimaryKey
    val userId: Int,
    val email: String,
    val userName: String,
    val userSurname: String,
    val scores: List<Score>
) {
    @Ignore
    val balance: Int = scores.sumOf { it.scoreBalance }
}