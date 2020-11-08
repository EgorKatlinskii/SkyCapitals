package com.nvvi9.skycapitalsmobile.di.modules

import com.nvvi9.skycapitalsmobile.ui.fragments.AuthFragment
import com.nvvi9.skycapitalsmobile.ui.fragments.LoginFragment
import com.nvvi9.skycapitalsmobile.ui.fragments.RegistrationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRegistrationFragment(): RegistrationFragment

    @ContributesAndroidInjector
    abstract fun contributeAuthFragment(): AuthFragment
}