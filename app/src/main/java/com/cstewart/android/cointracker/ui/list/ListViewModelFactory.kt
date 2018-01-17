package com.cstewart.android.cointracker.ui.list

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cstewart.android.cointracker.data.CoinRepository

class ListViewModelFactory(private val repository: CoinRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return ListViewModel(repository) as T
    }

}