package com.mohammad.bahadori.data.remote


import com.google.gson.JsonObject
import com.mohammad.bahadori.data.remote.model.BaseResponse
import com.mohammad.bahadori.data.remote.model.coin.response.CoinData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
//    companion object {
//        const val apiKey =
//            "3cbffc54-63a0-4cc4-a593-5902f9ed39ce"
//    }

    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun fetchAllCoinsWithStart(
       // @Header("X-CMC_PRO_API_KEY") token: String = apiKey,
        @Query("start") page: Int,
        @Query("limit") size: Int,
        @Query("convert") convert: String = "USD"
    ): Response<BaseResponse<List<CoinData>>>

    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun fetchAllCoins(
        // @Header("X-CMC_PRO_API_KEY") token: String = apiKey,
        @Query("limit") size: Int,
        @Query("convert") convert: String = "USD"
    ): Response<BaseResponse<List<CoinData>>>
    @GET("/v1/cryptocurrency/info")
    suspend fun fetchCoinDetail(
        @Query("id") id: Int,
    ): Response<JsonObject>

}