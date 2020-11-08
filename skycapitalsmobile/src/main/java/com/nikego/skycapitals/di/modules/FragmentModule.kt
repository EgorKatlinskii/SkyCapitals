package com.nikego.skycapitals.di.modules

import com.nikego.skycapitals.ui.fragments.AuthFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeAuthFragment(): AuthFragment
}