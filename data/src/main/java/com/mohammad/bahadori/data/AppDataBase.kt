package com.mohammad.bahadori.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohammad.bahadori.data.local.dao.CoinDao
import com.mohammad.bahadori.data.local.dao.CoinDetailDao
import com.mohammad.bahadori.data.local.model.CoinDetailEntity
import com.mohammad.bahadori.data.local.model.CoinEntity
import com.mohammad.bahadori.data.local.model.RemoteCoinKey

@Database(
    entities = [CoinEntity::class, CoinDetailEntity::class, RemoteCoinKey::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun coinDao(): CoinDao

    abstract fun detailedCoinDao(): CoinDetailDao

}