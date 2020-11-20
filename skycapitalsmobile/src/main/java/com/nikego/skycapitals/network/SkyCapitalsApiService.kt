package com.nikego.skycapitals.network

import com.nikego.skycapitals.data.Account
import retrofit2.http.Body
import retrofit2.http.POST

interface SkyCapitalsApiService {
    @POST("create")
    suspend fun registerAccount(@Body account: Account)
}