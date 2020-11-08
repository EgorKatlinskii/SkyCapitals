package com.nvvi9.skycapitalsmobile.di.modules

import com.nvvi9.skycapitalsmobile.database.LoginDataSource
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class DataSourceModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginDataSource(): LoginDataSource
}