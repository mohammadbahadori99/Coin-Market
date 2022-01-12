package com.mohammad.bahadori.data

import retrofit2.Response
import retrofit2.HttpException

@Throws(ServerException::class)
fun <T> Response<T>.bodyOrThrow(): T {
    if (!isSuccessful) throw HttpException(this)
    return body()!!
}
suspend inline fun <R, V> syncAndReadFromDB(
    crossinline createCall: suspend () -> R,
    crossinline loadFromDb: suspend () -> V,
    crossinline shouldFetch: suspend (V?) -> Boolean,
    crossinline saveCallResult: suspend (R) -> Unit
): V {
    val cache = loadFromDb()
    return if (cache == null || shouldFetch(cache)) {
        val result = createCall()
        saveCallResult(result)
        loadFromDb()
    } else {
        cache
    }
}
