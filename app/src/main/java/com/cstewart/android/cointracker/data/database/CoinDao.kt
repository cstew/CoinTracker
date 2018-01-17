package com.cstewart.android.cointracker.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.cstewart.android.cointracker.data.database.model.Symbol

@Dao
interface CoinDao {

    @Query("SELECT * FROM Symbol")
    fun getSymbols(): LiveData<List<Symbol>>

    @Insert
    fun insertSymbols(vararg symbol: Symbol)

    @Query("DELETE FROM Symbol")
    fun deleteAllSymbols()
}