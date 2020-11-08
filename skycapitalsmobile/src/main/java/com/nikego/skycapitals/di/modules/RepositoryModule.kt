package com.nikego.skycapitals.di.modules

import com.nikego.skycapitals.repositories.LoginRepositoryImpl
import com.nikego.skycapitals.repositories.base.LoginRepository
import dagger.Binds
import dagger.Module


@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}