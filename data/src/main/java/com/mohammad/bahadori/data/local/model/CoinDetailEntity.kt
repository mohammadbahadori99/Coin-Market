package com.mohammad.bahadori.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohammad.bahadori.domain.models.CoinDetailDomainModel

@Entity(tableName = "coin_detail_entity")
data class CoinDetailEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val des: String,
    val logo: String,
    val symbol: String,
    )

fun CoinDetailEntity.toCoinDetailDomainModel() = CoinDetailDomainModel(this.id,this.name,this.des,this.logo,this.symbol)