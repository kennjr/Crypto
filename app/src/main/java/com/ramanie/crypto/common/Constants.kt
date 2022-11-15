package com.ramanie.crypto.common

object Constants {

    const val BASE_URL = "https://api.coinpaprika.com/"

}

enum class ErrorTypes(val type: Int){
    UNEXPECTED_ERROR(0),
    UNEXPECTED_SERVER_RESPONSE(1),
    UNABLE_TO_COMMUNICATE_WITH_SERVER(2)
}