package com.example.quoteapp.Data.DailyQuote

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class TodayQuote(
    @PrimaryKey val id: Int = 1,
    val dayQuote : String,
    val dayAuthor : String,
    val dayAddQuote : String,
)
