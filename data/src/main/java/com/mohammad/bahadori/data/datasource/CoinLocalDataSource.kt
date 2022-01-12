package com.mohammad.bahadori.data.datasource

import com.mohammad.bahadori.domain.enums.CoinOrder
import com.mohammad.bahadori.data.local.model.CoinDetailEntity
import com.mohammad.bahadori.data.local.model.CoinEntity
import com.mohammad.bahadori.domain.enums.SortType

interface CoinLocalDataSource {
    fun getDetailedCoin(id:Int): CoinDetailEntity?
    fun getAllCoins(coinOrder: CoinOrder,sortType: SortType):List<CoinEntity>?
    suspend fun insertAllCoins(coinsList:List<CoinEntity>)
    suspend fun insertCoinDetail(coinDetail: CoinDetailEntity)
}