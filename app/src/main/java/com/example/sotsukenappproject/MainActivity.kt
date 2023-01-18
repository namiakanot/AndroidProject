package com.example.sotsukenappproject

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.example.sotsukenappproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var player: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        var tapCount = 0

        binding.reserbutton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        /**
         * どこかに透明のcheatButton作る
         */
        binding.cheatButton.setOnClickListener{
            tapCount++
            if(tapCount >= 7){
                binding.reserbutton.setOnClickListener{
                    startActivity(Intent(this,CheatModeActivity::class.java))
                    tapCount = 0
                }
            }
        }

        player = MediaPlayer.create(this, R.raw.sora)
        player.isLooping = true
    }
    override fun onResume(){
        super.onResume()
        player.start()
    }


}