package com.cstewart.android.cointracker.data.inject

import com.cstewart.android.cointracker.CoinApplication
import com.cstewart.android.cointracker.ui.list.ListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(CoinModule::class))
interface CoinComponent {

    fun inject(coinApplication: CoinApplication)

    fun inject(listActivity: ListActivity)

}