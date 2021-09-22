package com.example.NewsApp.Api

import com.example.NewsApp.Data.Article
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines?country=eg")
   suspend  fun getallNews(@Query("apiKey") apikey:String): Response


    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY="63b1f94dad044add871d1e319c630265"
    }
}