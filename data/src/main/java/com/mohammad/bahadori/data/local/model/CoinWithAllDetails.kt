package com.mohammad.bahadori.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.mohammad.bahadori.domain.models.CoinWithAllDetailDomainModel

data class CoinWithAllDetails(
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val coinEntity: CoinEntity,
    @Embedded
    val coinDetailEntity: CoinDetailEntity
)

fun CoinWithAllDetails.toCoinWithAllDetailDomainModel() = CoinWithAllDetailDomainModel(
    this.coinEntity.toCoinDomainModel(),
    this.coinDetailEntity.toCoinDetailDomainModel()
)