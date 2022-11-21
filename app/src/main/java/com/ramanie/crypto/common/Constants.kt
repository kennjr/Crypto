package com.ramanie.crypto.common

object Constants {

    const val BASE_URL = "https://api.coinpaprika.com/"
    const val PARAM_COIN_ID = "coinId"
}

sealed class ErrorTypes {
    data class UNEXPECTED_ERROR(val message: String?): ErrorTypes()
    data class UNEXPECTED_SERVER_RESPONSE(val message: String?): ErrorTypes()
    data class UNABLE_TO_COMMUNICATE_WITH_SERVER(val message: String?): ErrorTypes()
}