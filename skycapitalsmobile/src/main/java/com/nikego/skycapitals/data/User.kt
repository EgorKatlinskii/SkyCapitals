package com.nikego.skycapitals.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity
data class User(
    @PrimaryKey val userId: Int,
    @Json(name = "ostOffice") val email: String,
    val userName: String,
    val userSurname: String,
    val password: Int,
    val scores: List<Score>? = null
) {
    @Ignore
    val balance = scores?.sumOf { it.scoreBalance }
}