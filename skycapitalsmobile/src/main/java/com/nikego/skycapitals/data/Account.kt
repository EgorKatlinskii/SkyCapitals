package com.nikego.skycapitals.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = ["accountId", "email"], unique = true)])
data class Account(
        @PrimaryKey val accountId: String,
        val email: String,
        val firstName: String,
        val lastName: String,
        val password: String
)