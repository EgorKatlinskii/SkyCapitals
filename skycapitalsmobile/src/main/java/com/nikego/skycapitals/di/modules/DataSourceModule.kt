package com.nikego.skycapitals.di.modules

import com.nikego.skycapitals.database.AccountDataSource
import com.nikego.skycapitals.database.BankCardDataSource
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class DataSourceModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginDataSource(): AccountDataSource

    @ContributesAndroidInjector
    abstract fun contributeBankCardDataSource(): BankCardDataSource
}