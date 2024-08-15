package com.example.quoteapp.Data.DailyQuote

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteapp.Data.DataBase.MyApplication
import kotlinx.coroutines.launch

class TodayQuoteViewModel : ViewModel() {

    private val _quoteLiveData = MyApplication.roomDatabase2.dayDao()
    val quoteLiveData: LiveData<TodayQuote?> get() = _quoteLiveData.getQuote()

    fun insert(insertQuote:TodayQuote){
        viewModelScope.launch {
            _quoteLiveData.insert(insertQuote)
        }
    }



    }
