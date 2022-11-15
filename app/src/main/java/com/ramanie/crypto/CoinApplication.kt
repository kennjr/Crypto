package com.ramanie.crypto

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// we add this class so that hilt gets access to the application as well and it can therefore provide the context whenever a dependency needs it
@HiltAndroidApp
class CoinApplication: Application()