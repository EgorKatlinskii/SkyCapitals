package com.nikego.skycapitals.di.modules

import android.content.Context
import androidx.room.Room
import com.nikego.skycapitals.database.EBankDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideDb(context: Context) =
            Room.databaseBuilder(context, EBankDatabase::class.java, "EBank.db")
                    .fallbackToDestructiveMigration()
                    .build()

    @Provides
    fun provideAccountDao(database: EBankDatabase) =
            database.accountDao

    @Provides
    fun provideBankCardDao(database: EBankDatabase) =
            database.bankCardDao

    @Provides
    fun provideIODispatcher() = Dispatchers.IO
}