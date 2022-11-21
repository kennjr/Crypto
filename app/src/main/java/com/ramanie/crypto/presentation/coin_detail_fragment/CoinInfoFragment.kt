package com.ramanie.crypto.presentation.coin_detail_fragment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.ramanie.crypto.R
import com.ramanie.crypto.common.ErrorTypes
import com.ramanie.crypto.presentation.coin_detail_fragment.components.CoinTag
import com.ramanie.crypto.presentation.coin_detail_fragment.components.TeamListItem

@Composable
fun CoinInfoFragment(
    navController: NavController,
    // this is one of the pros of DI we can get an instance of the VM with ease
    viewModel: CoinInfoViewModel = hiltViewModel()
) {

    val state = viewModel.state


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        state.coinInfo?.let {
            LazyColumn(modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)){
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(modifier = Modifier.weight(8f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start) {
                            Text(text = "${it.rank}.", style = MaterialTheme.typography.headlineSmall)
                            Text(text = "${it.name} (${it.symbol})", overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.headlineMedium)
                        }
                        Row(modifier = Modifier.weight(2f),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically) {
                            Text(text = if (it.isActive) stringResource(id = R.string.active) else
                                stringResource(id = R.string.in_active),
                                color = if (it.isActive) Color.Green else Color.Red,
                                fontStyle = FontStyle.Italic,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = it.description,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = stringResource(id = R.string.tags), style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    // this is a row that will wrap the elements if they exceed the set bounds i.e
                    // push the rest of the content to the next line
                    FlowRow(modifier = Modifier.fillMaxWidth(),
                        // this is for the spacing between the items in the composable
                        mainAxisSpacing = 8.dp, crossAxisSpacing = 8.dp) {
                        it.tags.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = stringResource(id = R.string.team_members), style = MaterialTheme.typography.headlineSmall)
                }
                // we'll put the list of team members out here bc the list is dynamic so we don't know
                // how many we'll have to populate
                items(it.team.size){ index ->
                    TeamListItem(team = it.team[index], modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp))
                    if (index < it.team.size.minus(1)){
                        Divider()
                    }
                }
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
            CircularProgressIndicator(modifier = Modifier
                .wrapContentSize()
                .align(Alignment.TopCenter))
        }
    }
    
}