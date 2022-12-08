package com.example.sotsukenappproject

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
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

        //ダイアログ表示
        val fragmentManager: FragmentManager = supportFragmentManager

        var Begin = findViewById<ImageButton>(R.id.reserbutton)
        var Continue = findViewById<ImageButton>(R.id.continuebutton)


        //後で消す
        binding.atckbutton.setOnClickListener{
            val intent = Intent(this, AttackCountDownActivity::class.java)
            startActivity(intent)
        }

        // 続きから(元)
//        Continue.setOnClickListener {
//            val intent = Intent(this, GameActivity::class.java)
//            startActivity(intent)
//        }
        //続きから
        binding.reserbutton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
//            val dialog = NameFragment()
//            fragmentManager?.run{
//                dialog.show(this,"username")
//            }
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