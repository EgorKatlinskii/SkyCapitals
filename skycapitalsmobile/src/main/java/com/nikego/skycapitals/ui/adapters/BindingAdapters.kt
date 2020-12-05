package com.nikego.skycapitals.ui.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.nikego.skycapitals.data.Currency

@BindingAdapter("app:currency", "app:balance")
fun TextView.setBalance(currency: Currency, balance: Int) {
    text = currency.sign + balance
}