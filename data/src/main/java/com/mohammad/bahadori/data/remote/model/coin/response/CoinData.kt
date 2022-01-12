package com.mohammad.bahadori.data.remote.model.coin.response

import com.google.gson.annotations.SerializedName


data class CoinData(

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("num_market_pairs") val num_market_pairs: Int,
    @SerializedName("date_added") val date_added: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("max_supply") val max_supply: Double?,
    @SerializedName("circulating_supply") val circulating_supply: Double?,
    @SerializedName("total_supply") val total_supply: Double?,
    @SerializedName("cmc_rank") val cmc_rank: Int,
    @SerializedName("last_updated") val last_updated: String,
    @SerializedName("quote") val quote: Quote
)

data class Quote(

    @SerializedName("USD") val usd: USD
)

data class USD(

    @SerializedName("price") val price: Double,
    @SerializedName("volume_24h") val volume_24h: Double,
    @SerializedName("percent_change_1h") val percent_change_1h: Double,
    @SerializedName("percent_change_24h") val percent_change_24h: Double,
    @SerializedName("percent_change_7d") val percent_change_7d: Double,
    @SerializedName("percent_change_30d") val percent_change_30d: Double,
    @SerializedName("percent_change_60d") val percent_change_60d: Double,
    @SerializedName("percent_change_90d") val percent_change_90d: Double,
    @SerializedName("market_cap") val market_cap: Double,
    @SerializedName("last_updated") val last_updated: String
)

