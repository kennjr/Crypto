package com.ramanie.crypto.data.mapper

import com.ramanie.crypto.data.remote.dto.CoinDto
import com.ramanie.crypto.domain.model.Coin

object CoinMapper {

    fun CoinDto.toCoin(): Coin{
        return Coin(id = id, isActive = isActive, isNew = isNew, name = name, rank = rank, symbol = symbol, type = type)
    }

    fun Coin.toCoinDto(): CoinDto{
        return CoinDto(id = id, isActive = isActive, isNew = isNew, name = name, rank = rank, symbol = symbol, type = type)
    }

}