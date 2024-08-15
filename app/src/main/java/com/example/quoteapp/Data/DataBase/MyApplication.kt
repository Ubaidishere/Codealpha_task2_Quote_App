package com.example.quoteapp.Data.DataBase

import android.app.Application
import androidx.room.Room
import com.example.quoteapp.Data.DailyQuote.DataBaseTodayQuote

class MyApplication : Application() {

    companion object {
        lateinit var roomDatabase: Database

        lateinit var roomDatabase2: DataBaseTodayQuote

    }

    override fun onCreate() {
        super.onCreate()

        roomDatabase = Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "database"
        ).build()


        roomDatabase2 = Room.databaseBuilder(
            applicationContext,
            DataBaseTodayQuote::class.java,
            "databasetodayquote"
        ).build()
    }


    }