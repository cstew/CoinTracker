package com.cstewart.android.cointracker

import android.app.Application
import com.cstewart.android.cointracker.data.inject.CoinComponent
import com.cstewart.android.cointracker.data.inject.CoinModule
import com.cstewart.android.cointracker.data.inject.DaggerCoinComponent

class CoinApplication: Application() {

    companion object {
        lateinit var graph: CoinComponent
    }

    override fun onCreate() {
        super.onCreate()

        graph = DaggerCoinComponent.builder()
                .coinModule(CoinModule(this))
                .build()
        graph.inject(this)
    }

}