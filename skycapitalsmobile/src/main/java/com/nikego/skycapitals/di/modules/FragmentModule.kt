package com.nikego.skycapitals.di.modules

import com.nikego.skycapitals.ui.fragments.AuthFragment
import com.nikego.skycapitals.ui.fragments.LoginFragment
import com.nikego.skycapitals.ui.fragments.RegistrationFragment
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