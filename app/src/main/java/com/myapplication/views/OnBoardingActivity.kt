package com.myapplication.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// Splash screen
class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}