package com.cstewart.android.cointracker.data.inject

import android.arch.persistence.room.Room
import android.content.Context
import com.cstewart.android.cointracker.data.CoinRepository
import com.cstewart.android.cointracker.data.database.CoinDatabase
import com.cstewart.android.cointracker.data.network.BitfinexService
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class CoinModule(val appContext: Context) {

    @Provides
    @Singleton
    fun provideCoinRepository(bitfinexService: BitfinexService, coinDatabase: CoinDatabase): CoinRepository
            = CoinRepository(bitfinexService, coinDatabase)

    @Provides
    @Singleton
    fun provideCoinDatabase(): CoinDatabase {
        return Room.databaseBuilder(appContext, CoinDatabase::class.java, "coin-database")
                .build()
    }

    @Provides
    @Singleton
    fun provideBitfinexService(): BitfinexService {
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.bitfinex.com/v1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return retrofit.create(BitfinexService::class.java)
    }
}