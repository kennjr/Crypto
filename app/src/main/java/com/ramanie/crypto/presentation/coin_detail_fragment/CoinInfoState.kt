package com.ramanie.crypto.presentation.coin_detail_fragment

import com.ramanie.crypto.common.ErrorTypes
import com.ramanie.crypto.domain.model.Coin
import com.ramanie.crypto.domain.model.CoinInfo

data class CoinInfoState(
    val isLoading: Boolean = false,
    val coinInfo: CoinInfo? = null,
    val error: ErrorTypes? = null
)
