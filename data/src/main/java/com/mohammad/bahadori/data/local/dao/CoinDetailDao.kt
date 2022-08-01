package com.mohammad.bahadori.data.local.dao

import androidx.room.*
import com.mohammad.bahadori.data.local.model.CoinDetailEntity
import com.mohammad.bahadori.data.local.model.CoinWithAllDetails

@Dao
interface CoinDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailedCoin(coinDetailEntity: CoinDetailEntity)

    @Query("SELECT * FROM coin_detail_entity WHERE id = :id")
    fun getDetailedCoin(id: Int): CoinDetailEntity?


    @Transaction
    @Query("SELECT * FROM coin_detail_entity WHERE id = :coinId")
    fun getCoinWithAllDetail(coinId: Int): CoinWithAllDetails
}