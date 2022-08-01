package com.mohammad.bahadori.data.datasource

import com.mohammad.bahadori.data.bodyOrThrow
import com.mohammad.bahadori.data.local.model.CoinDetailEntity
import com.mohammad.bahadori.data.remote.WebService
import com.mohammad.bahadori.data.remote.model.coin.response.CoinData
import com.mohammad.bahadori.data.remote.model.detailedCoin.response.CoinDetailData
import com.mohammad.bahadori.data.remote.model.detailedCoin.response.CoinDetailResponse
import javax.inject.Inject

class CoinRemoteDataSourceImpl @Inject constructor(
    private val apiService: WebService,
) : CoinRemoteDataSource {
    override suspend fun fetchDetailedCoin(id: Int): CoinDetailEntity {
        val data= apiService.fetchCoinDetail(id)
        val coinDetailResponse=data.body()?.getAsJsonObject("data")
        val coinDetailResponse2=coinDetailResponse?.getAsJsonObject(id.toString())
        val boz=CoinDetailResponse(CoinDetailData.getFromJsonObject(coinDetailResponse2!!))
        return CoinDetailEntity(boz.coinDetailData.id,
            boz.coinDetailData.name,
            boz.coinDetailData.description,
            boz.coinDetailData.logo,
        boz.coinDetailData.symbol)
    }

    override suspend fun fetchAllCoins(): List<CoinData>{
        return apiService.fetchAllCoinsWithStart(1,100).bodyOrThrow().data
    }
}