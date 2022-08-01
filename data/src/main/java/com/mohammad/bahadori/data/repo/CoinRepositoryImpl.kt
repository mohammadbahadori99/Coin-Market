package com.mohammad.bahadori.data.repo

import android.util.Log
import com.mohammad.bahadori.data.syncAndReadFromDB
import com.mohammad.bahadori.data.datasource.CoinLocalDataSource
import com.mohammad.bahadori.data.datasource.CoinRemoteDataSource
import com.mohammad.bahadori.domain.enums.CoinOrder
import com.mohammad.bahadori.data.local.model.CoinDetailEntity
import com.mohammad.bahadori.data.local.model.CoinEntity
import com.mohammad.bahadori.data.local.model.toCoinWithAllDetailDomainModel
import com.mohammad.bahadori.domain.enums.SortType
import com.mohammad.bahadori.domain.models.CoinDetailDomainModel
import com.mohammad.bahadori.domain.models.CoinDomainModel
import com.mohammad.bahadori.domain.models.CoinWithAllDetailDomainModel
import com.mohammad.bahadori.domain.repo.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinLocalDataSource: CoinLocalDataSource,
    private val coinRemoteDataSource: CoinRemoteDataSource,
) : CoinRepository {

    override suspend fun getAllCoins(
        coinOrder: CoinOrder,
        sortType: SortType
    ): List<CoinDomainModel> {

        return syncAndReadFromDB(
            createCall = {
                coinRemoteDataSource.fetchAllCoins()
            },
            loadFromDb = {
                coinLocalDataSource.getAllCoins(coinOrder = coinOrder, sortType = sortType)!!.map {
                    CoinDomainModel(
                        it.position,
                        it.id,
                        it.name,
                        it.symbol,
                        it.price.toString(),
                        it.percentChange,
                        it.volumeChange,
                        it.marketCap,
                        it.circulatingSupply
                    )
                }
            },
            shouldFetch = {
                (it?.size ?: 0) == 0
            },
            saveCallResult = { list ->
                coinLocalDataSource.insertAllCoins(list.map {
                    CoinEntity(
                        0,
                        it.id,
                        it.name,
                        it.symbol,
                        it.quote.usd.price,
                        it.quote.usd.percent_change_24h.toString(),
                        it.quote.usd.volume_24h.toString(),
                        it.quote.usd.market_cap.toString(),
                        it.circulating_supply
                    )
                })
            }
        )
    }

    override suspend fun getDetailedCoin(
        id: Int,
    ): CoinWithAllDetailDomainModel {
        return syncAndReadFromDB(
            createCall = {
                coinRemoteDataSource.fetchDetailedCoin(id)
            },
            loadFromDb = {
                val detailTemp = coinLocalDataSource.getDetailedCoin(id)
                if (detailTemp != null) {
                    val temp = coinLocalDataSource.getCoinRelation(id)
                    temp.toCoinWithAllDetailDomainModel()
                } else {
                    null
                }
            },
            shouldFetch = {
                it == null
            },
            saveCallResult = {
                coinLocalDataSource.insertCoinDetail(
                    CoinDetailEntity(
                        it.id,
                        it.name,
                        it.des,
                        it.logo,
                        it.symbol
                    )
                )
            }
        )
    }
}