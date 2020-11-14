package com.nikego.skycapitals.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = ["cardId"], unique = true)])
data class BankCard(
        @PrimaryKey val cardId: String,
        @ColumnInfo(index = true) val cardHolderId: String,
        val cardHolderName: String,
        val expireTimeMillis: Long,
        var balance: Double = .0
)