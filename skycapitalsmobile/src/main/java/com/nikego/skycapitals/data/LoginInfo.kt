package com.nikego.skycapitals.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class LoginInfo(
        @PrimaryKey val id: String,
        val email: String,
        val firstName: String,
        val lastName: String,
        val password: String
)