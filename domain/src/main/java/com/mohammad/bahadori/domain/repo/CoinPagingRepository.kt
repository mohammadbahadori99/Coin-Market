package com.mohammad.bahadori.domain.repo

import androidx.paging.PagingData
import com.mohammad.bahadori.domain.models.CoinDomainModel
import kotlinx.coroutines.flow.Flow

interface CoinPagingRepository {
    fun getCoinByPage(pageSize:Int): Flow<PagingData<CoinDomainModel>>
}