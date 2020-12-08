package com.nikego.skycapitals.network

import android.util.Log
import com.nikego.skycapitals.data.BankCard
import com.nikego.skycapitals.data.Currency
import com.nikego.skycapitals.data.Score
import com.nikego.skycapitals.data.User
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.data.skycapitalsserver.UserLogin
import com.nikego.skycapitals.data.skycapitalsserver.UserRegister
import com.nikego.skycapitals.network.retrofit.SkyCapitalsApiService
import javax.inject.Inject
import kotlin.random.Random


class SkyCapitalsDataSource @Inject constructor(private val skyCapitalsApiService: SkyCapitalsApiService) {

    private val user = User(
        userId = Random.nextInt(),
        email = "sobaka@gmail.ru",
        userName = "Pitro",
        userSurname = "Pitrov",
        password = 1234,
        scores = List(15) {
            Score(
                Random.nextInt(),
                listOf(Currency.USD, Currency.BYN, Currency.EUR).random(),
                getBankCards()
            )
        }
    )

    private fun getBankCards() = List((1..5).random()) {
        BankCard(Random.nextInt(), "card name", Random.nextInt(0, 1000))
    }

    suspend fun login(userLogin: UserLogin) =
        try {
            skyCapitalsApiService.loginUser(userLogin.email, userLogin.password).let {
                Result.Success(it)
            }
        } catch (t: Throwable) {
            Result.Error(t)
        }

    suspend fun register(userRegister: UserRegister) =
        try {
            skyCapitalsApiService.registerUser(userRegister).let {
                Result.Success(it)
            }
        } catch (t: Throwable) {
            Log.e(SkyCapitalsDataSource::class.java.simpleName, t.stackTraceToString())
            Result.Error(t)
        }
}