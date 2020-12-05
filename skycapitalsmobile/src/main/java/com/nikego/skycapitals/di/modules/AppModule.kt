package com.nikego.skycapitals.di.modules

import android.content.Context
import androidx.room.Room
import com.nikego.skycapitals.database.EBankDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class, RetrofitModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideDb(context: Context) =
        Room.databaseBuilder(context, EBankDatabase::class.java, "EBank.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUserDao(database: EBankDatabase) =
        database.userDao

    @Provides
    fun provideIODispatcher() = Dispatchers.IO
}