package com.example.quoteapp.Data.Unsplash

import com.example.quoteapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiServices {

    @Headers("Authorization: Client-ID ${BuildConfig.Api_Key}")
    @GET("photos/random")
    suspend fun getRandomPhoto(
        @Query("query") query: String = "night,dark,black"
    ): ImageResources

}
