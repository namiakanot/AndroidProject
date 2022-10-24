package com.example.sotsukenappproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sotsukenappproject.databinding.ActivityMainBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
    }
}