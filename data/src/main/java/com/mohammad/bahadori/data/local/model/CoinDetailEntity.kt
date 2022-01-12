package com.mohammad.bahadori.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_detail_entity")
data class CoinDetailEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val des: String,
    val logo: String
)