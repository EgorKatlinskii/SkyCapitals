package com.nikego.skycapitals.network.retrofit

import com.nikego.skycapitals.data.BankCard
import com.nikego.skycapitals.data.User
import com.nikego.skycapitals.data.skycapitalsserver.ScoreCreate
import com.nikego.skycapitals.data.skycapitalsserver.TransactionStatus
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

    @POST("create/score/{email}")
    suspend fun addScore(@Path("email") email: String): ScoreCreate

    @GET("create/card/{cardType}/{scoreNumber}")
    suspend fun addBankCard(
        @Path("cardType") cardType: String,
        @Path("scoreNumber") scoreNumber: Int
    ): BankCard

    @POST("by_card_number/{cardNumber}/{receiverCardNumber}/{sum}")
    suspend fun sendTransaction(
        @Path("cardNumber") cardNumber: Long,
        @Path("receiverCardNumber") receiverCardNumber: Long,
        @Path("sum") sum: Int
    ): TransactionStatus
}