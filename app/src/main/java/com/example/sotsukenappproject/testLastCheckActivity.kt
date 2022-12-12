package com.example.sotsukenappproject

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sotsukenappproject.databinding.ActivityTestLastCheckBinding

class testLastCheckActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestLastCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTestLastCheckBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        binding.backButton.setOnClickListener{
            val intent = Intent(this, ChoosePrefectureActivity::class.java )
            startActivity(intent)
        }
        // 進行を開始する
        binding.startAttackButton.setOnClickListener{
            val intent = Intent(this, AttackCountDownActivity2::class.java )
            startActivity(intent)
        }
    }
}