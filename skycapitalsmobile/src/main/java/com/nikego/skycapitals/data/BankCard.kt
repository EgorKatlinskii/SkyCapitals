package com.nikego.skycapitals.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = ["cardId"], unique = true)],
        foreignKeys = [
            ForeignKey(
                    entity = Account::class,
                    parentColumns = ["accountId"],
                    childColumns = ["cardHolderId"]
            )
        ]
)
data class BankCard(
        @PrimaryKey val cardId: String,
        val cardHolderId: String,
        val cardHolderName: String,
        val expireTimeMillis: Long,
        var balance: Double = .0
)