package com.mohammad.bahadori.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.mohammad.bahadori.data.local.model.CoinEntity
import com.mohammad.bahadori.data.local.model.RemoteCoinKey

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(coinEntities: List<CoinEntity>)

    @RawQuery(observedEntities = [CoinEntity::class])
    fun getAllCoinsWithPaging(query: SupportSQLiteQuery): PagingSource<Int, CoinEntity>

    @Query("SELECT id FROM coin_entity")
    suspend fun getRecordsCount(): List<Int>

    //    @Query("SELECT * FROM coin_entity ORDER BY :order ASC")
//    fun getAllCoinsSortAsc(order: String): List<CoinEntity>
//
//
//    @Query("SELECT * FROM coin_entity ORDER BY :order DESC")
//    fun getAllCoinsSortDesc(order: String): List<CoinEntity>
    @Query("DELETE FROM coin_entity")
    suspend fun clearAll()

    @Query("SELECT * FROM remotecoinkey WHERE id =:id")
    suspend fun getRemoteKey(id: Int): RemoteCoinKey

    @Query("DELETE FROM remotecoinkey")
    suspend fun deleteAllRemoteKeys()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteCoinKey(list: List<RemoteCoinKey>)


    @Query("SELECT * FROM coin_entity ORDER BY name ASC")
    fun getAllCoinsSortNameAsc(): List<CoinEntity>


    @Query("SELECT * FROM coin_entity ORDER BY name DESC")
    fun getAllCoinsSortNameDesc(): List<CoinEntity>


    @Query("SELECT * FROM coin_entity ORDER BY price ASC")
    fun getAllCoinsSortPriceAsc(): List<CoinEntity>


    @Query("SELECT * FROM coin_entity ORDER BY price DESC")
    fun getAllCoinsSortPriceDesc(): List<CoinEntity>


    @Query("SELECT * FROM coin_entity ORDER BY marketCap ASC")
    fun getAllCoinsSortMarketCapAsc(): List<CoinEntity>


    @Query("SELECT * FROM coin_entity ORDER BY marketCap DESC")
    fun getAllCoinsSortMarketCapDesc(): List<CoinEntity>


    @Query("SELECT * FROM coin_entity ORDER BY circulatingSupply ASC")
    fun getAllCoinsSortCirculatingSupplyAsc(): List<CoinEntity>


    @Query("SELECT * FROM coin_entity ORDER BY circulatingSupply DESC")
    fun getAllCoinsSortCirculatingSupplyDesc(): List<CoinEntity>

}