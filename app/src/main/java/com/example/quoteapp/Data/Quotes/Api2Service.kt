package com.example.quoteapp.Data.Quotes

import retrofit2.http.GET

interface Api2Service {

    @GET("random")
    suspend fun getQuotes() : List<QuoteResources>
}