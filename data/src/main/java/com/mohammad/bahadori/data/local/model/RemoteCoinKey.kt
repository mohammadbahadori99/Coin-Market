package com.mohammad.bahadori.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteCoinKey (
    @PrimaryKey
    val id:Int,
    val nextKey:Int?)