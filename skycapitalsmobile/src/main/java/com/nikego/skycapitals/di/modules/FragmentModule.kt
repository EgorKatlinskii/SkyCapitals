package com.nikego.skycapitals.di.modules

import com.nikego.skycapitals.ui.fragments.AuthFragment
import com.nikego.skycapitals.ui.fragments.BalanceFragment
import com.nikego.skycapitals.ui.fragments.ScoreFragment
import com.nikego.skycapitals.ui.fragments.TransactionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeAuthFragment(): AuthFragment

    @ContributesAndroidInjector
    abstract fun contributeBalanceFragment(): BalanceFragment

    @ContributesAndroidInjector
    abstract fun contributeScoreFragment(): ScoreFragment

    @ContributesAndroidInjector
    abstract fun contributeTransactionFragment(): TransactionFragment
}