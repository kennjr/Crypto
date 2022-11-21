package com.ramanie.crypto.presentation.coins_fragment

import com.ramanie.crypto.common.ErrorTypes
import com.ramanie.crypto.domain.model.Coin

data class CoinsState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: ErrorTypes? = null
)
