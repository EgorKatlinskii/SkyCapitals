package com.nikego.skycapitals.network.retrofit

import com.nikego.skycapitals.data.User
import com.nikego.skycapitals.data.skycapitalsserver.UserLogin
import retrofit2.http.Body
import retrofit2.http.POST


interface SkyCapitalsApiService {

    @POST("create")
    suspend fun registerAccount(@Body user: User)

    @POST("login")
    suspend fun loginUser(@Body userLogin: UserLogin): User
}