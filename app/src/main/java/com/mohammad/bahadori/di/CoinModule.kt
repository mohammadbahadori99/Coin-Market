package com.mohammad.bahadori.di

import com.mohammad.bahadori.data.datasource.CoinLocalDataSource
import com.mohammad.bahadori.data.datasource.CoinLocalDataSourceImpl
import com.mohammad.bahadori.data.datasource.CoinRemoteDataSource
import com.mohammad.bahadori.data.datasource.CoinRemoteDataSourceImpl
import com.mohammad.bahadori.data.repo.CoinRepositoryImpl
import com.mohammad.bahadori.data.repo.CoinPagingPagingRepositoryImpl
import com.mohammad.bahadori.domain.repo.CoinPagingRepository
import com.mohammad.bahadori.domain.repo.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoinModule {

    @Binds
    abstract fun provideCoinRepository(
        coinRepositoryImpl: CoinRepositoryImpl
    ): CoinRepository


    @Binds
    abstract fun provideCoinLocalDataSource(coinLocalDataSourceImpl: CoinLocalDataSourceImpl): CoinLocalDataSource

    @Binds
    abstract fun bindDbCoinRepository(coinPagingRepositoryImpl: CoinPagingPagingRepositoryImpl): CoinPagingRepository

    @Binds
    abstract fun provideCoinRemoteDataSource(coinRemoteDataSourceImpl: CoinRemoteDataSourceImpl): CoinRemoteDataSource
}