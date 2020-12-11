package com.nikego.skycapitals.network

import android.util.Log
import com.nikego.skycapitals.data.CardType
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.data.skycapitalsserver.UserLogin
import com.nikego.skycapitals.data.skycapitalsserver.UserRegister
import com.nikego.skycapitals.network.retrofit.SkyCapitalsApiService
import javax.inject.Inject


class SkyCapitalsDataSource @Inject constructor(private val skyCapitalsApiService: SkyCapitalsApiService) {

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

    suspend fun addScore(email: String) =
        try {
            skyCapitalsApiService.addScore(email).let {
                Result.Success(it)
            }
        } catch (t: Throwable) {
            Log.e(SkyCapitalsDataSource::class.java.simpleName, t.stackTraceToString())
            Result.Error(t)
        }

    suspend fun addBankCard(cardType: CardType, scoreNumber: Int) =
        try {
            skyCapitalsApiService.addBankCard(cardType.type, scoreNumber).let {
                Result.Success(it)
            }
        } catch (t: Throwable) {
            Log.e(SkyCapitalsDataSource::class.java.simpleName, t.stackTraceToString())
            Result.Error(t)
        }
}