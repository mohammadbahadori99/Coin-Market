package com.mohammad.bahadori.data

class ServerException(message: String, val code: Int = -1) : Exception(message)
