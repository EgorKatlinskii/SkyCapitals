package com.nikego.skycapitals.data.skycapitalsserver

import com.squareup.moshi.Json


data class ScoreCreate(@Json(name = "numberScore:") val numberScore: Int)