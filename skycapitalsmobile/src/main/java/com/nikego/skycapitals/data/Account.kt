package com.nikego.skycapitals.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(indices = [Index(value = ["accountId", "email"], unique = true)])
data class Account(
        @PrimaryKey @field:Json(name = "userId") val accountId: String,
        @field:Json(name = "ostOffice") val email: String,
        @field:Json(name = "userName") val firstName: String,
        @field:Json(name = "userSurname") val lastName: String,
        @field:Json(name = "userPassword") val password: String
)