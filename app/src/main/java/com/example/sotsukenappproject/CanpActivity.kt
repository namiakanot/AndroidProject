package com.example.sotsukenappproject

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.os.CountDownTimer
import android.widget.TextView
import com.example.sotsukenappproject.databinding.ActivityCanpBinding

class CanpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCanpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCanpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val startTime:Long = 10000


        // 戻るを押すとメイン画面(戦闘画面)へ遷移
        binding.backbutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var textMessage = findViewById<TextView>(R.id.syotyouword)

        var small = findViewById<ImageButton>(R.id.small_grow)
        var middle = findViewById<ImageButton>(R.id.middle_grow)
        var large = findViewById<ImageButton>(R.id.large_grow)

        //  ボタン押したとき
        small.setOnClickListener{
            textMessage.setText(R.string.textsmall)
        }

        middle.setOnClickListener{
            textMessage.setText(R.string.textmiddle)
        }

        large.setOnClickListener{
            textMessage.setText(R.string.textlarge)
        }

        // 戻るを押すとメイン画面(戦闘画面)へ遷移
        binding.growButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
