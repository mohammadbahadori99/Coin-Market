package com.mohammad.bahadori.data.remote.model.detailedCoin.response

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
data class CoinDetailData(

    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("symbol") var symbol: String,
    @SerializedName("description") var description: String,
    @SerializedName("logo") var logo: String,
) {
    companion object {
        fun getFromJsonObject(
            jsonObject: JsonObject
        ): CoinDetailData {
            return CoinDetailData(
                id = jsonObject.get("id").asInt,
                name = jsonObject.get("name").asString,
                logo = jsonObject.get("logo").asString,
                symbol = jsonObject.get("symbol").asString,
                description = jsonObject.get("description").asString,
            )
        }
    }

}