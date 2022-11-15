package com.ramanie.crypto.domain.repository

import com.ramanie.crypto.data.remote.dto.CoinDto
import com.ramanie.crypto.data.remote.dto.CoinInfoDto

// the reason we wrap the model repo in an interface is bc that's good for testing use-cases
// In the domain layer we're gonna define the fun.s that we'll use to access the actual repo that's in the data-layer
interface CoinRepo {

    // we're gonna have one fun. for each API request that we've got in the API interface
    // If we ever decide to add caching to the app then we can add the fun.s that insert the data into the room tbl.s here

    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinInfoDto

}