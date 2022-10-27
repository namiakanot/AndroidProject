package com.example.sotsukenappproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sotsukenappproject.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGameBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
    }
}