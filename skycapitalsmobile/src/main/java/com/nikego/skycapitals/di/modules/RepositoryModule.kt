package com.nikego.skycapitals.di.modules

import com.nikego.skycapitals.repositories.UserRepositoryImpl
import com.nikego.skycapitals.repositories.base.UserRepository
import dagger.Binds
import dagger.Module


@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}