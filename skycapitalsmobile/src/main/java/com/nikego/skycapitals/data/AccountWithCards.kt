package com.nikego.skycapitals.data

import androidx.room.Embedded
import androidx.room.Relation


data class AccountWithCards(
        @Embedded val account: Account,
        @Relation(
                parentColumn = "accountId",
                entityColumn = "cardHolderId"
        )
        val bankCards: List<BankCard>
)