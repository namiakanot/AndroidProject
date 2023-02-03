package com.example.sotsukenappproject

import android.content.Intent
import android.media.MediaPlayer
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
            if(tapCount >= 4){
                binding.textView23.text = "てすと"
                binding.reserbutton.setOnClickListener{
                    startActivity(Intent(this,CheatModeActivity::class.java))
                    tapCount = 0
                    binding.textView23.text = "すたあと"
                }
            }
        }
        //if(player == null) {
            player = MediaPlayer.create(this, R.raw.sora)
            player.isLooping = true
            player.start()
        //}
    }
    override fun onResume(){
        super.onResume()
        player.start()
    }


}