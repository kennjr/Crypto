package com.ramanie.crypto.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id: String,
    // we're using the annotation below to tell the data class that what we receive will be
    // "is_active" but we're gonna refer to it as isActive
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)