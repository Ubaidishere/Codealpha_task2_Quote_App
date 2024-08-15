package com.example.quoteapp.Data.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomData(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val quote : String
)
