package com.example.quoteapp.Data.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {

    @Insert
    suspend fun insertQuote(quote:RoomData)

    @Query("SELECT * FROM RoomData ORDER BY id DESC")
    fun getLikedQuote(): LiveData<List<RoomData>>

    @Query("DELETE FROM RoomData WHERE id = :id")
    suspend fun deleteQuote(id:Int)

    @Query("SELECT COUNT(*) FROM RoomData WHERE quote = :quote")
    fun isQuoteLiked(quote: String): LiveData<Int>

}