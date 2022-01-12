package com.mohammad.bahadori.domain.models


data class CoinDomainModel(
    val position: Int = 0,
    val id: Int,
    val name: String,
    val symbol: String?,
    val price: String?,
    val percentChange: String?,
    val volumeChange: String?,
    val marketCap: String?,
    val circulatingSupply: Double?
)
