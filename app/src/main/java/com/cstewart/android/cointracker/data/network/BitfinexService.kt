package com.cstewart.android.cointracker.data.network

import retrofit2.Call
import retrofit2.http.GET

interface BitfinexService {

    @GET("symbols")
    fun getSymbols(): Call<List<String>>

}