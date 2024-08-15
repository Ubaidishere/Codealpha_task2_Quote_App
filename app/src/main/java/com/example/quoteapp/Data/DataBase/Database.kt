package com.example.quoteapp.Data.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase


@Database([RoomData::class], version = 1)
abstract class Database : RoomDatabase(){

    abstract fun getDao() : Dao
}