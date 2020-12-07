package com.nikego.skycapitals.ui.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.nikego.skycapitals.data.Currency
import com.nikego.skycapitals.vo.BankCardItem
import com.nikego.skycapitals.vo.ScoreItem

@BindingAdapter("app:currency", "app:scores")
fun TextView.setBalance(currency: Currency?, scores: List<ScoreItem>?) {
    if (currency != null && scores != null) {
        text = "${currency.sign} ${
            scores.filter {
                it.currency == currency
            }.sumOf { it.scoreBalance }
        }"
    }
}

@BindingAdapter("app:cardsAmount")
fun TextView.setCardsAmount(cards: List<BankCardItem>?) {
    if (cards != null) {
        text = cards.size.let {
            if (it == 1) "$it CARD" else "$it CARDS"
        }
    }
}

@BindingAdapter("app:currency", "app:balance")
fun TextView.setCurrencyBalance(currency: Currency?, balance: Int?) {
    if (currency != null && balance != null) {
        text = currency.sign + balance
    }
}