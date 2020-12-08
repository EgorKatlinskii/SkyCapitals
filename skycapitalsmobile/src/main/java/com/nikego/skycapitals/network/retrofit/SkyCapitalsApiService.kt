package com.nikego.skycapitals.network.retrofit

import com.nikego.skycapitals.data.User
import com.nikego.skycapitals.data.skycapitalsserver.UserRegister
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface SkyCapitalsApiService {

    @POST("create")
    suspend fun registerUser(@Body user: UserRegister): User

    @GET("authorization/{email}/{password}")
    suspend fun loginUser(@Path("email") email: String, @Path("password") password: Int): User
}