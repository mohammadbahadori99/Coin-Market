package com.mohammad.bahadori.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import androidx.sqlite.db.SimpleSQLiteQuery
import com.mohammad.bahadori.data.AppDataBase
import com.mohammad.bahadori.data.enum.AppConstant
import com.mohammad.bahadori.data.local.model.CoinEntity
import com.mohammad.bahadori.domain.models.CoinDomainModel
import com.mohammad.bahadori.domain.repo.CoinPagingRepository
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class CoinPagingPagingRepositoryImpl @Inject constructor(
    private val db: AppDataBase,
    private val coinRemoteMediator: CoinRemoteMediator,
) :
    CoinPagingRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getCoinByPage(pageSize: Int) = Pager(
        config = PagingConfig(pageSize),
        remoteMediator = coinRemoteMediator
    ) {
        val myQuery =
            SimpleSQLiteQuery("SELECT * FROM coin_entity  ORDER BY ${AppConstant.coinOrder.value} ${AppConstant.sortType.name}")
        db.coinDao().getAllCoinsWithPaging(myQuery)
    }.flow.mapLatest { pagingData ->
        pagingData.map { entity ->
            entity.toCoinDomainModel()
        }
    }
}


fun CoinEntity.toCoinDomainModel(): CoinDomainModel =
    CoinDomainModel(
        this.position,
        this.id,
        this.name,
        this.symbol,
        this.price.toString(),
        this.percentChange,
        this.volumeChange,
        this.marketCap,
        this.circulatingSupply
    )