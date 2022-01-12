package com.mohammad.bahadori.data.local.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_entity")
data class
CoinEntity(
    val position: Int = 0,
    @PrimaryKey
    val id: Int,
    val name: String,
    val symbol: String?,
    val price: Double?,
    val percentChange: String?,
    val volumeChange: String?,
    val marketCap: String?,
    val circulatingSupply: Double?
)