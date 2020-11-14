package com.nikego.skycapitals

import androidx.room.Embedded
import androidx.room.Relation
import com.nikego.skycapitals.data.Account
import com.nikego.skycapitals.data.BankCard


data class AccountWithCards(
        @Embedded val account: Account,
        @Relation(
                parentColumn = "accountId",
                entityColumn = "cardHolderId"
        )
        val bankCards: List<BankCard>
)