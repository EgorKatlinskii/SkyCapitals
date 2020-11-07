package com.nvvi9.skycapitalsmobile.di.modules

import com.nvvi9.skycapitalsmobile.ui.fragments.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule{

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment():LoginFragment
}