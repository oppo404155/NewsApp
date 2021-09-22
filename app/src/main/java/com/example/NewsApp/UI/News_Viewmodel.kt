package com.example.NewsApp.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.NewsApp.Data.NewsRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class News_Viewmodel @Inject constructor(private val repositry: NewsRepositry):ViewModel() {
    fun getallnews()=repositry.getallNews().asLiveData()
}