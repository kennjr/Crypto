package com.ramanie.crypto.data.mapper

import com.ramanie.crypto.data.remote.dto.CoinInfoDto
import com.ramanie.crypto.domain.model.CoinInfo

object CoinInfoMapper {

    fun CoinInfoDto.toCoinInfo(): CoinInfo{
        return CoinInfo(description, developmentStatus, firstDataAt, hardwareWallet, hashAlgorithm, id,
            isActive, isNew, lastDataAt, links, linksExtended, logo, message, name, openSource,
            orgStructure, proofType, rank, startedAt, symbol, tags, team, type, whitepaper)
    }

    fun CoinInfo.toCoinInfoDto(): CoinInfoDto{
        return CoinInfoDto(description, developmentStatus, firstDataAt, hardwareWallet, hashAlgorithm, id,
            isActive, isNew, lastDataAt, links, linksExtended, logo, message, name, openSource,
            orgStructure, proofType, rank, startedAt, symbol, tags, team, type, whitepaper)
    }

}