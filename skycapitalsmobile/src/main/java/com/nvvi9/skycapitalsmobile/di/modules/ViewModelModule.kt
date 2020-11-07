package com.nvvi9.skycapitalsmobile.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nvvi9.skycapitalsmobile.di.factories.ViewModelFactory
import com.nvvi9.skycapitalsmobile.di.keys.ViewModelKey
import com.nvvi9.skycapitalsmobile.ui.viewmodels.LoginViewModel
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
    @Singleton
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}