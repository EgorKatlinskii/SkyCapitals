package com.nvvi9.skycapitalsmobile.di.modules

import com.nvvi9.skycapitalsmobile.repositories.LoginRepositoryImpl
import com.nvvi9.skycapitalsmobile.repositories.base.LoginRepository
import dagger.Binds
import dagger.Module


@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}