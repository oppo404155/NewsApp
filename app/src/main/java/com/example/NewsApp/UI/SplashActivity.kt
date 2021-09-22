package com.example.NewsApp.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.NewsApp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler= Handler().apply {
            postDelayed({
                val intent=Intent(this@SplashActivity,MainActivity::class.java)
                startActivity(intent)
            },4000)
        }


    }
}