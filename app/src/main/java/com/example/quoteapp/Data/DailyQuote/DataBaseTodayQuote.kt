package com.example.quoteapp.Data.DailyQuote

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodayQuote::class], version = 1)
abstract class DataBaseTodayQuote : RoomDatabase() {
    abstract fun dayDao(): TodayQuoteDao

}
