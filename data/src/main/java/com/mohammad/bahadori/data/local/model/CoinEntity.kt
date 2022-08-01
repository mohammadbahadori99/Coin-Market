package com.mohammad.bahadori.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohammad.bahadori.domain.models.CoinDomainModel

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

fun CoinEntity.toCoinDomainModel() = CoinDomainModel(
    this.position,
    this.id,
    this.name,
    this.symbol,
    if (this.price.toString() == "null") 0.toString() else this.price.toString(),
    this.percentChange,
    this.volumeChange,
    this.marketCap,
    this.circulatingSupply
)