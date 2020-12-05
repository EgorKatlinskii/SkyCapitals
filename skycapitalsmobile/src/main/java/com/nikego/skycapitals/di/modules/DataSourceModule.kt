package com.nikego.skycapitals.di.modules

import com.nikego.skycapitals.database.UserDataSource
import com.nikego.skycapitals.network.SkyCapitalsDataSource
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class DataSourceModule {

    @ContributesAndroidInjector
    abstract fun contributeUserDataSource(): UserDataSource

    @ContributesAndroidInjector
    abstract fun contributeSkyCapitalsDataSource(): SkyCapitalsDataSource
}