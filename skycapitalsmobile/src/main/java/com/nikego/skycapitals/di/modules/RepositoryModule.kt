package com.nikego.skycapitals.di.modules

import com.nikego.skycapitals.repositories.BankCardRepositoryImpl
import com.nikego.skycapitals.repositories.LoginRepositoryImpl
import com.nikego.skycapitals.repositories.base.BankCardRepository
import com.nikego.skycapitals.repositories.base.LoginRepository
import dagger.Binds
import dagger.Module


@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun bindBankCardRepository(bankCardRepositoryImpl: BankCardRepositoryImpl): BankCardRepository
}