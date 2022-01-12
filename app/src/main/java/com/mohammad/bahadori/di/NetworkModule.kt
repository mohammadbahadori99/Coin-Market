package com.mohammad.bahadori.di

import com.mohammad.bahadori.data.remote.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    const val apiKey = "3cbffc54-63a0-4cc4-a593-5902f9ed39ce"


    // Okhttp
    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor { chain ->
            var request = chain.request()
            val url = request.url.newBuilder().build()
            request = request.newBuilder()
                .addHeader("X-CMC_PRO_API_KEY", apiKey)
                .url(url)
                .build()
            chain.proceed(request)
        }
        .build()

    // Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://pro-api.coinmarketcap.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideWebService(retrofit: Retrofit): WebService =
        retrofit.create(WebService::class.java)


}