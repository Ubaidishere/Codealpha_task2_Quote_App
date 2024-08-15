package com.example.quoteapp.Data.DailyQuote

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TodayQuoteDao {

        @Query("SELECT * FROM TodayQuote WHERE id = 1")
        fun getQuote(): LiveData<TodayQuote?>

        @Upsert
        suspend fun insert(insertQuote: TodayQuote)

    }

