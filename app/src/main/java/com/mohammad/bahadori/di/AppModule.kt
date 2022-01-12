package com.mohammad.bahadori.di

import android.content.Context
import androidx.room.Room
import com.mohammad.bahadori.data.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, "coin_market_cap_app").build()
}