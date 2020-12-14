package com.nikego.skycapitals.domain.mappers

import com.nikego.skycapitals.data.BaseMapper
import com.nikego.skycapitals.data.User
import com.nikego.skycapitals.vo.BankCardItem
import com.nikego.skycapitals.vo.ScoreItem
import com.nikego.skycapitals.vo.UserItem
import java.util.*


object UserItemMapper : BaseMapper<User, UserItem> {

    override fun map(type: User): UserItem =
        type.run {
            UserItem(
                userId = userId,
                name = "$userName $userSurname",
                scores = scores.map { score ->
                    ScoreItem(score.scoreNumber, score.currency, score.bankCards.map {
                        BankCardItem(
                            it.numberCard,
                            "$userSurname $userName".toUpperCase(Locale.ROOT),
                            score.currency,
                            it.cardType,
                            it.balance
                        )
                    })
                }
            )
        }
}