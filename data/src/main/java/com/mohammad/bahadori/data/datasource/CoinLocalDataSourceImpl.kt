package com.mohammad.bahadori.data.datasource

import com.mohammad.bahadori.domain.enums.CoinOrder
import com.mohammad.bahadori.data.local.dao.CoinDao
import com.mohammad.bahadori.data.local.dao.CoinDetailDao
import com.mohammad.bahadori.data.local.model.CoinDetailEntity
import com.mohammad.bahadori.data.local.model.CoinEntity
import com.mohammad.bahadori.domain.enums.SortType
import javax.inject.Inject

class CoinLocalDataSourceImpl @Inject constructor(
    private val coinDao: CoinDao,
    private val coinDetailDao: CoinDetailDao
) : CoinLocalDataSource {

    override fun getDetailedCoin(id: Int): CoinDetailEntity? {
        return coinDetailDao.getDetailedCoin(id)
    }

    override fun getAllCoins(coinOrder: CoinOrder, sortType: SortType): List<CoinEntity> {
        return when (sortType) {
            SortType.ASC -> {
                when (coinOrder) {
                    CoinOrder.NAME -> {
                        coinDao.getAllCoinsSortNameAsc()
                    }
                    CoinOrder.MARKET_CAP -> {
                        coinDao.getAllCoinsSortMarketCapAsc()
                    }
                    CoinOrder.PRICE -> {
                        coinDao.getAllCoinsSortPriceAsc()
                    }
                    CoinOrder.CIRCULATING_SUPPLY -> {
                        coinDao.getAllCoinsSortCirculatingSupplyAsc()
                    }
                    CoinOrder.POSITION -> {
                        coinDao.getAllCoinsSortCirculatingSupplyAsc()
                    }
                }
            }
            SortType.DESC -> {
                when (coinOrder) {
                    CoinOrder.NAME -> {
                        coinDao.getAllCoinsSortNameDesc()
                    }
                    CoinOrder.MARKET_CAP -> {
                        coinDao.getAllCoinsSortMarketCapDesc()
                    }
                    CoinOrder.PRICE -> {
                        coinDao.getAllCoinsSortPriceDesc()
                    }
                    CoinOrder.CIRCULATING_SUPPLY -> {
                        coinDao.getAllCoinsSortCirculatingSupplyDesc()
                    }
                    CoinOrder.POSITION -> {
                        coinDao.getAllCoinsSortCirculatingSupplyDesc()
                    }
                }
            }
        }
    }

    override suspend fun insertAllCoins(coinsList: List<CoinEntity>) {
        coinDao.insertAll(coinsList)
    }

    override suspend fun insertCoinDetail(coinDetail: CoinDetailEntity) {
        coinDetailDao.insertDetailedCoin(coinDetail)
    }

}