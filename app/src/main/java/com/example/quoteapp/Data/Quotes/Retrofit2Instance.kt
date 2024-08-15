package com.example.quoteapp.Data.Quotes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit2Instance {

    private val retrofit2 = Retrofit.Builder()
        .baseUrl("https://zenquotes.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: Api2Service = retrofit2.create(Api2Service::class.java)
}