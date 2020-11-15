package com.nikego.skycapitals.data.converters

import androidx.room.TypeConverter
import com.nikego.skycapitals.data.Currency


class CurrencyConverter {

    @TypeConverter
    fun toCurrency(value: String) =
            Currency.valueOf(value)

    @TypeConverter
    fun toName(currency: Currency) =
            currency.name
}