package com.example.sotsukenappproject

import android.content.Intent
import android.media.MediaPlayer
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sotsukenappproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var player: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        var Begin = findViewById<ImageButton>(R.id.reserbutton)
        var Continue = findViewById<ImageButton>(R.id.continuebutton)

        //初めから
        Begin.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
        // 続きから
        Continue.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        // 設定へ
        binding.settingmode.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        // 育成へ
        binding.canpmode.setOnClickListener {
            val demo = Intent(this, CampActivity::class.java)
            startActivity(demo)
        }
        //　実績へ
        binding.achievementbutton.setOnClickListener {
            val intent = Intent(this, AchievementActivity::class.java)
            startActivity(intent)
        }

        player = MediaPlayer.create(this, R.raw.sora)
        player.isLooping = true
    }
    override fun onResume(){
        super.onResume()
        player.start()
    }

    override fun onPause(){
        super.onPause()
        player.pause()
    }
}