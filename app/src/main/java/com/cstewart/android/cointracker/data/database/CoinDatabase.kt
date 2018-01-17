package com.cstewart.android.cointracker.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.cstewart.android.cointracker.data.database.model.Symbol

@Database(entities = arrayOf(Symbol::class), version = 1)
abstract class CoinDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao

}