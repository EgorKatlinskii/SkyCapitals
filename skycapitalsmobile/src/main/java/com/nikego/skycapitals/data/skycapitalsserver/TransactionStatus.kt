package com.nikego.skycapitals.data.skycapitalsserver

import com.squareup.moshi.Json

data class TransactionStatus(
   @Json(name = "Статус операции:") val status: String
)