package com.nvvi9.skycapitalsmobile.di

import android.app.Application
import android.content.Context
import com.nvvi9.skycapitalsmobile.SkyCapitalsApplication
import com.nvvi9.skycapitalsmobile.di.modules.AppModule
import com.nvvi9.skycapitalsmobile.di.modules.DataSourceModule
import com.nvvi9.skycapitalsmobile.di.modules.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, DataSourceModule::class, RepositoryModule::class])
interface AppComponent : AndroidInjector<SkyCapitalsApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    override fun inject(app: SkyCapitalsApplication)
}