package com.example.quoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.quoteapp.Data.DailyQuote.TodayQuoteViewModel
import com.example.quoteapp.Data.DataBase.myViewModel
import com.example.quoteapp.ui.theme.QuoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuoteAppTheme {
                val viewMode = ViewModelProvider(this)[myViewModel::class.java]
                val viewMode2 = ViewModelProvider(this)[TodayQuoteViewModel::class.java]
                ScreenNavigation(viewMode,viewMode2)
            }
        }
    }
}
