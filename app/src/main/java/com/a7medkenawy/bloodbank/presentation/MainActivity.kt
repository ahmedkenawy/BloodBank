package com.a7medkenawy.bloodbank.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a7medkenawy.bloodbank.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }
}