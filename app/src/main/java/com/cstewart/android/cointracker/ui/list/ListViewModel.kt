package com.cstewart.android.cointracker.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.cstewart.android.cointracker.data.CoinRepository
import com.cstewart.android.cointracker.data.database.model.Symbol

class ListViewModel(val repository: CoinRepository) : ViewModel() {

    fun getSymbols(): LiveData<List<Symbol>> {
        return repository.getSymbols()
    }

}