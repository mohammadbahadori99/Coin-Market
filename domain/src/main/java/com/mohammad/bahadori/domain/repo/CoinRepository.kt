package com.mohammad.bahadori.domain.repo

import com.mohammad.bahadori.domain.enums.CoinOrder
import com.mohammad.bahadori.domain.enums.SortType
import com.mohammad.bahadori.domain.models.CoinDetailDomainModel
import com.mohammad.bahadori.domain.models.CoinDomainModel
import com.mohammad.bahadori.domain.models.CoinWithAllDetailDomainModel

interface CoinRepository {
    suspend fun getDetailedCoin(id:Int): CoinWithAllDetailDomainModel
    suspend fun getAllCoins(coinOrder: CoinOrder =CoinOrder.NAME,sortType: SortType = SortType.ASC):List<CoinDomainModel>
}