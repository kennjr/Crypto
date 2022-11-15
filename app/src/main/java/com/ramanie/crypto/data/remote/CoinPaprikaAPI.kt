package com.ramanie.crypto.data.remote

import com.ramanie.crypto.data.remote.dto.CoinDto
import com.ramanie.crypto.data.remote.dto.CoinInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

// in this interface we're gon define the diff routes that we'd like to make requests to
interface CoinPaprikaAPI {

    @GET("/v1/coins")
    // we'll simply pass the List<CoinDto> as our preferred return type,
    // the parsing(converting from json to data class) will be done(automatically) by a g-son
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coin/{coinId}")
    suspend fun getCoinData(@Path("coinId") coinId: String): CoinInfoDto
}