package com.example.NewsApp.Data

import com.example.NewsApp.Api.NewsApi
import com.example.NewsApp.Api.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositry @Inject constructor(private val newsApi: NewsApi){

    fun  getallNews()=flow{
        emit(newsApi.getallNews(NewsApi.API_KEY))

    }


}