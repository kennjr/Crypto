package com.ramanie.crypto.presentation.coins_fragment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ramanie.crypto.R
import com.ramanie.crypto.common.ErrorTypes
import com.ramanie.crypto.domain.model.Coin
import com.ramanie.crypto.presentation.Screen
import com.ramanie.crypto.presentation.coins_fragment.components.CoinListItem

@Composable
fun CoinsList(
    navController: NavController,
    // this is one of the pros of DI we can get an instance of the VM with ease
    viewModel: CoinsViewModel = hiltViewModel()
) {

    val state = viewModel.state

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.coins){ item: Coin ->
                CoinListItem(coin = item, onItemClick = {
                    navController.navigate(Screen.CoinInfoFragment.route + "/${it.id}")
                })
            }
        }
        if (state.error != null){
            Text(text = when(state.error){
                is ErrorTypes.UNABLE_TO_COMMUNICATE_WITH_SERVER -> {
                    stringResource(id = R.string.unable_to_communicate_with_server)
                }
                is ErrorTypes.UNEXPECTED_ERROR -> {
                    stringResource(id = R.string.unexpected_error,
                        state.error.message ?: stringResource(id = R.string.unavailable))
                }
                is ErrorTypes.UNEXPECTED_SERVER_RESPONSE -> {
                    stringResource(id = R.string.unexpected_server_response,
                        state.error.message ?: stringResource(id = R.string.unavailable))
                }
            }, color = MaterialTheme.colorScheme.error, textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
                )

        }

        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.wrapContentSize().align(Alignment.TopCenter))
        }

    }
    
}