package com.nikego.skycapitals.di.modules

import com.nikego.skycapitals.database.LoginDataSource
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class DataSourceModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginDataSource(): LoginDataSource
}