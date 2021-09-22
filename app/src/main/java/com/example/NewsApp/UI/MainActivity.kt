package com.example.NewsApp.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.NewsApp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onBackPressed() {
        finishAffinity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}