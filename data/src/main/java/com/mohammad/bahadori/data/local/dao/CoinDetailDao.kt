package com.mohammad.bahadori.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohammad.bahadori.data.local.model.CoinDetailEntity

@Dao
interface CoinDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailedCoin(coinDetailEntity: CoinDetailEntity)

    @Query("SELECT * FROM coin_detail_entity WHERE id = :id")
    fun getDetailedCoin(id: Int): CoinDetailEntity?
}