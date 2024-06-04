package com.ramanie.crypto.data.mapper

import com.ramanie.crypto.data.remote.dto.CoinInfoDto
import com.ramanie.crypto.domain.model.CoinInfo

object CoinInfoMapper {

    fun CoinInfoDto?.toCoinInfo(): CoinInfo?{
        return if (this != null) CoinInfo(description, developmentStatus ?: "No development status", firstDataAt, hardwareWallet, hashAlgorithm ?: "No algorithm", id,
            isActive, isNew, lastDataAt, links, linksExtended, logo, message ?: "no message", name, openSource,
            orgStructure ?: "No structure", proofType ?: "Proof type unavailable", rank, startedAt ?: "null", symbol, tags, team, type ?: "null", whitepaper)
        else null
    }

    fun CoinInfo.toCoinInfoDto(): CoinInfoDto{
        return CoinInfoDto(description, developmentStatus, firstDataAt, hardwareWallet, hashAlgorithm, id,
            isActive, isNew, lastDataAt, links, linksExtended, logo, message, name, openSource,
            orgStructure, proofType, rank, startedAt, symbol, tags, team, type, whitepaper)
    }

}