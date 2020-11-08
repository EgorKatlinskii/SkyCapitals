package com.nikego.skycapitals.di

import android.app.Application
import android.content.Context
import com.nikego.skycapitals.SkyCapitalsApplication
import com.nikego.skycapitals.di.modules.AppModule
import com.nikego.skycapitals.di.modules.DataSourceModule
import com.nikego.skycapitals.di.modules.MainActivityModule
import com.nikego.skycapitals.di.modules.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    DataSourceModule::class,
    RepositoryModule::class,
    MainActivityModule::class
])
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