package com.ramanie.crypto.data.repository

import com.ramanie.crypto.data.remote.CoinPaprikaAPI
import com.ramanie.crypto.data.remote.dto.CoinDto
import com.ramanie.crypto.data.remote.dto.CoinInfoDto
import com.ramanie.crypto.domain.repository.CoinRepo
import javax.inject.Inject


class CoinRepoImpl @Inject constructor(private val api: CoinPaprikaAPI): CoinRepo {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinInfoDto {
        return api.getCoinData(coinId)
    }

}