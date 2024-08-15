package com.example.quoteapp.Data.DataBase

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class myViewModel : ViewModel() {

    private val _data = MyApplication.roomDatabase.getDao()
    val data = _data.getLikedQuote()

    fun insertQuote(quote:RoomData){
        viewModelScope.launch {
            _data.insertQuote(quote)
        }
    }

    fun deleteQuote(id:Int){
        viewModelScope.launch {
            _data.deleteQuote(id)
        }
    }


    fun isQuoteLiked(quote: String): LiveData<Int> {
        return _data.isQuoteLiked(quote)
    }


}