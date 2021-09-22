package com.example.NewsApp.Hilt

import com.example.NewsApp.Api.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modul {
   @Singleton
   @Provides
   fun provideRetrofit():Retrofit=
       Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
           .baseUrl(NewsApi.BASE_URL).build()
    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit)=retrofit.create(NewsApi::class.java)

}