package com.ramanie.crypto.presentation.coins_fragment

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramanie.crypto.common.Resource
import com.ramanie.crypto.domain.use_cases.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * The MAIN purpose of the VM is to maintain our state, now that we moved most of our business logic
 * to the usecase. State = UI state(screen rotation, e.tc), ...
 */

@HiltViewModel
class CoinsViewModel @Inject constructor(
    // we're injecting the usecase instead of the repo since this is the right approach when working with clean-arch
    private val getCoinsUseCase: GetCoinsUseCase
) :ViewModel(){

    // this is the method used in the stocks app
    var state by mutableStateOf(CoinsState())

    // the reason we've got a private and public version is bc we don't want the state to be modified
    // by a component, so we'll expose the immutable state to the components
//    private val _state = mutableStateOf(CoinsState())
//    val state: State<CoinsState> = _state

    init {
        getCoins()
    }

    // this fun will execute our usecase and put the result into our state
    private fun getCoins(){
        // this is a class but since we overide the invoke fun we can call it kinda like a fun.
        // we use the onEach so that we can execute some code whenever the flow emits smth
        getCoinsUseCase().onEach { result ->
            when(result){
                is Resource.Loading -> {
                    //we update the value of the isLoading property of the data-class
                    state = state.copy(isLoading = true)
                    // this is the other way of updating the value
//                    _state.value = CoinsState(isLoading = true)
                }
                is Resource.Success -> {
                    state = state.copy(coins = result.data ?: emptyList(), isLoading = false, error = null)
                    // this is the other way of updating the value
//                    _state.value = CoinsState(error = result.data ?: emptyList()
                }
                is Resource.Error -> {
                    state = state.copy(error = result.error, isLoading = false)
                    // this is the other way of updating the value
//                    _state.value = CoinsState(error = result.error)
                }
            }
        }
            // we're launching the flow in a coroutine bc flows are async
            .launchIn(viewModelScope)
    }

}