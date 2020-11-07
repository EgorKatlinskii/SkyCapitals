package com.nvvi9.skycapitalsmobile.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class LoginInfo(
        @PrimaryKey val email: String,
        val password: String
)