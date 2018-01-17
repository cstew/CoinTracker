package com.cstewart.android.cointracker.data

import android.arch.lifecycle.LiveData
import android.util.Log
import com.cstewart.android.cointracker.data.database.CoinDatabase
import com.cstewart.android.cointracker.data.database.model.Symbol
import com.cstewart.android.cointracker.data.network.BitfinexService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinRepository(val bitfinexService: BitfinexService, val coinDatabase: CoinDatabase) {

    val coinDao = coinDatabase.coinDao()
    var symbolsLoaded = false

    fun getSymbols() : LiveData<List<Symbol>> {
        loadSymbols()
        return coinDao.getSymbols()
    }

    fun loadSymbols() {
        if (symbolsLoaded) {
            return
        }

        bitfinexService.getSymbols().enqueue(object: Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>?, t: Throwable?) {
                Log.e("CoinRepository", "Get symbols failure", t)
            }

            override fun onResponse(call: Call<List<String>>?, response: Response<List<String>>?) {
                symbolsLoaded = true

                // TODO: Use a better threading model
                Thread() {
                    response?.body()?.let {
                        coinDao.deleteAllSymbols()

                        it.forEach {
                            coinDao.insertSymbols(Symbol(it))
                        }
                    }
                }.start()
            }

        })
    }

}