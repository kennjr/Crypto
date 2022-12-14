package com.ramanie.crypto.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ramanie.crypto.common.Constants
import com.ramanie.crypto.presentation.coin_detail_fragment.CoinInfoFragment
import com.ramanie.crypto.presentation.coins_fragment.CoinsList
import com.ramanie.crypto.presentation.ui.theme.CryptoTheme
import dagger.hilt.android.AndroidEntryPoint

// the annotation below will allow dagger-hilt to inject the needed dependencies right here
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.CoinsFragment.route){
                        composable(Screen.CoinsFragment.route){
                            CoinsList(navController = navController)
                        }
                        composable(Screen.CoinInfoFragment.route + "/{${Constants.PARAM_COIN_ID}}"){
                            CoinInfoFragment(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CryptoTheme {
        Greeting("Android")
    }
}