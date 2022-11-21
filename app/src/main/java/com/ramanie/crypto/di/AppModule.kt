package com.ramanie.crypto.di

import com.ramanie.crypto.common.Constants
import com.ramanie.crypto.data.remote.CoinPaprikaAPI
import com.ramanie.crypto.data.repository.CoinRepoImpl
import com.ramanie.crypto.domain.repository.CoinRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
// Installing the dependencies in the SingletonComponent class will make sure that the dependencies, in the module, lives as long as the app
@InstallIn(SingletonComponent::class)
object AppModule {

    // this is added bc the dependency provides sth
    @Provides
    // this is added bc we'll need only one instance of this dependency
    @Singleton
    fun provideCoinPaprikaAPI(): CoinPaprikaAPI{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            // this will make sure that any data coming from the API is converted using Gson
            .addConverterFactory(GsonConverterFactory.create()).build().create(CoinPaprikaAPI::class.java)
    }

    // this is added bc the dependency provides sth
    @Provides
    // this is added bc we'll need only one instance of this dependency
    @Singleton
    fun provideCoinRepo(api: CoinPaprikaAPI): CoinRepo {
        // this one's easy simply bc dagger already knows how to create an instance of the API we'll
        // just pass the API as a param and then pass it as an argument in the return statement
        return CoinRepoImpl(api)
    }

}