package com.ramanie.crypto.presentation

// this is a helper class that we'll use to navigate to the diff frag.s that we've got in our app
sealed class Screen(val route: String){
    object CoinsFragment: Screen("coins_fragment")
    object CoinInfoFragment: Screen("coin_info_fragment")
}
