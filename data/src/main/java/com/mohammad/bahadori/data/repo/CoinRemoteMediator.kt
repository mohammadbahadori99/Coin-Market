package com.mohammad.bahadori.data.repo


import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mohammad.bahadori.data.AppDataBase
import com.mohammad.bahadori.data.local.dao.CoinDao
import com.mohammad.bahadori.data.local.model.CoinEntity
import com.mohammad.bahadori.data.remote.WebService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class CoinRemoteMediator @Inject constructor(
    private val db: AppDataBase,
    private val coinService: WebService
) : RemoteMediator<Int, CoinEntity>() {
    private val coinDao: CoinDao = db.coinDao()
    private var position=0
    override suspend fun initialize(): InitializeAction {
        // Require that remote REFRESH is launched on initial load and succeeds before launching
        // remote PREPEND / APPEND.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CoinEntity>
    ): MediatorResult {
        try {
            // Get the closest item from PagingState that we want to load data around.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    null
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {

                    // Query DB for SubredditRemoteKey for the subreddit.
                    // SubredditRemoteKey is a wrapper object we use to keep track of page keys we
                    // receive from the Reddit API to fetch the next or previous page.
                    val remoteKey = coinDao.getRecordsCount().size

                    // We must explicitly check if the page key is null when appending, since the
                    // Reddit API informs the end of the list by returning null for page key, but
                    // passing a null key to Reddit API will fetch the initial page.
                    if (remoteKey > 12000) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remoteKey
                }
            }

            val data = coinService.fetchAllCoinsWithStart(
                page = loadKey?:1,
                size = when (loadType) {
                    LoadType.REFRESH -> state.config.initialLoadSize
                    else -> state.config.pageSize
                }
            ).body()?.data
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    coinDao.clearAll()
                    coinDao.deleteAllRemoteKeys()
                }
                data?.let { list ->
                    coinDao.insertAll(
                        list.map {
                            CoinEntity(
                                position++,
                                it.id,
                                it.name,
                                it.symbol,
                                it.quote.usd.price,
                                it.quote.usd.percent_change_24h.toString(),
                                it.quote.usd.volume_24h.toString(),
                                it.quote.usd.market_cap.toString(),
                                it.circulating_supply
                            )
                        }
                    )
                }
            }
            return MediatorResult.Success(endOfPaginationReached = data?.isEmpty() ?: true)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}





