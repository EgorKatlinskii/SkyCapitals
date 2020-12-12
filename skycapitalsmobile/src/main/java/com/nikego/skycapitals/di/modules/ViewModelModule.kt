package com.nikego.skycapitals.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikego.skycapitals.di.factories.ViewModelFactory
import com.nikego.skycapitals.di.keys.ViewModelKey
import com.nikego.skycapitals.ui.viewmodels.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    abstract fun bindRegistrationViewModel(registrationViewModel: RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BalanceViewModel::class)
    abstract fun bindBalanceViewModel(balanceViewModel: BalanceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScoreViewModel::class)
    abstract fun bindScoreViewModel(scoreViewModel: ScoreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BankCardViewModel::class)
    abstract fun bindBankCardViewModel(bankCardViewModel: BankCardViewModel): ViewModel

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}