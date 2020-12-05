package com.nikego.skycapitals.di.modules

import com.nikego.skycapitals.network.retrofit.SkyCapitalsApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideSkyCapitalsApiService(moshi: Moshi): SkyCapitalsApiService = Retrofit.Builder()
            .baseUrl("http://localhost:8081/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(SkyCapitalsApiService::class.java)
}