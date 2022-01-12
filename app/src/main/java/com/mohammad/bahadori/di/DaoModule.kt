package com.mohammad.bahadori.di

import com.mohammad.bahadori.data.AppDataBase
import com.mohammad.bahadori.data.local.dao.CoinDao
import com.mohammad.bahadori.data.local.dao.CoinDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun provideCoinDao(db: AppDataBase): CoinDao = db.coinDao()

    @Provides
    fun provideDetailedCoinDao(db: AppDataBase): CoinDetailDao = db.detailedCoinDao()

}