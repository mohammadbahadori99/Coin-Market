package com.mohammad.bahadori.data.remote.model

import com.google.gson.annotations.SerializedName


data class BaseResponse<T>(
    @SerializedName("status") var status: Status? = null,
    @SerializedName("data") var data: T
)
