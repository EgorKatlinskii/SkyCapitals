package com.nikego.skycapitals.network

import com.nikego.skycapitals.data.BankCard
import com.nikego.skycapitals.data.Currency
import com.nikego.skycapitals.data.Score
import com.nikego.skycapitals.data.User
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.data.skycapitalsserver.UserLogin
import com.nikego.skycapitals.network.retrofit.SkyCapitalsApiService
import javax.inject.Inject
import kotlin.random.Random


class SkyCapitalsDataSource @Inject constructor(private val skyCapitalsApiService: SkyCapitalsApiService) {

    private val user = User(
        userId = Random.nextInt(),
        email = "sobaka@gmail.ru",
        userName = "Pitro",
        userSurname = "Pitrov",
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
            Result.Success(user)
        } catch (t: Throwable) {
            Result.Error(t)
        }
}