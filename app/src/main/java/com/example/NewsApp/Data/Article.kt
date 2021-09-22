package com.example.NewsApp.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Article (
    val source: Source,
    val author: String? = null,
    val title: String,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String? = null
):Parcelable
@Parcelize
data class Source (
    val id: String? = null,
    val name: String
):Parcelable
