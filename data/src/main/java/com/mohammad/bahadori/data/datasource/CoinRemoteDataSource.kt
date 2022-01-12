package com.mohammad.bahadori.data.datasource

import com.mohammad.bahadori.data.local.model.CoinDetailEntity
import com.mohammad.bahadori.data.remote.model.coin.response.CoinData

interface CoinRemoteDataSource {
    suspend fun fetchDetailedCoin(id: Int): CoinDetailEntity
    suspend fun fetchAllCoins(): List<CoinData>
}