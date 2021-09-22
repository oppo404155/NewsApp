package com.example.NewsApp.Api

import com.example.NewsApp.Data.Article

data class Response (
    val status: String,
    val totalResults: Long,
    val articles: List<Article>
)