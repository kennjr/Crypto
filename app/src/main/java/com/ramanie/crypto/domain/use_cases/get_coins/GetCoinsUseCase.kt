package com.ramanie.crypto.domain.use_cases.get_coins

import com.ramanie.crypto.common.ErrorTypes
import com.ramanie.crypto.common.Resource
import com.ramanie.crypto.data.mapper.CoinMapper.toCoin
import com.ramanie.crypto.domain.model.Coin
import com.ramanie.crypto.domain.repository.CoinRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    // NOTE : DO NOT inject the impl, instead inject the interface, this makes the injected interface replaceable
    private val repo: CoinRepo) {

    // by overriding the invoke fun. we can call the class GetCoinUseCase as if it were a fun.
    // we're returning a Flow since we'd like to emit multiple values over a period of time e.g the current state : loading, error and success
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            // the first step is to display the progressbar since the user is making a request,
            // we'd like to show him that we're working on it
            emit(Resource.Loading<List<Coin>>())
            val coins = repo.getCoins().map {
                it.toCoin()
            }
            // if the code didn't escape to the catch, then the request was successful
            emit(Resource.Success<List<Coin>>(data = coins))
        }
        // this is the exception we get if the response received is a non-200 response
        catch (e: HttpException){
            emit(Resource.Error<List<Coin>>(error = ErrorTypes.UNEXPECTED_SERVER_RESPONSE(e.localizedMessage)))
        }
        // this is the exception that we'll get whenever the repo/API can't communicate with the remote API eg: when there's no internet connection
        catch (e: IOException){
            // TODO (11/15/22) - do internet connection check, then either show no_internet or server_down msg
            emit(Resource.Error<List<Coin>>(error = ErrorTypes.UNABLE_TO_COMMUNICATE_WITH_SERVER(e.localizedMessage)))
        }
    }

}