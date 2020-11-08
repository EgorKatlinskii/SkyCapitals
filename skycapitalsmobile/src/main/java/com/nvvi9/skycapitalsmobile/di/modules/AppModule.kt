package com.nvvi9.skycapitalsmobile.di.modules

import android.content.Context
import androidx.room.Room
import com.nvvi9.skycapitalsmobile.database.LoginDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideDb(context: Context) =
            Room.databaseBuilder(context, LoginDatabase::class.java, "Login.db")
                    .fallbackToDestructiveMigration()
                    .build()

    @Provides
    fun provideLoginDao(database: LoginDatabase) =
            database.loginInfoDao

    @Provides
    fun provideIODispatcher() = Dispatchers.IO
}