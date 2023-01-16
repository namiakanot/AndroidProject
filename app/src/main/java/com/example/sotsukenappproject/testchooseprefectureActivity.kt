package com.example.sotsukenappproject

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.example.sotsukenappproject.databinding.ActivityTestchooseprefectureBinding

class testchooseprefectureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestchooseprefectureBinding
    private lateinit var player: MediaPlayer

    private lateinit var soundPool: SoundPool
    private var soundResId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTestchooseprefectureBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        //GAME遷移
        binding.backbutton2.setOnClickListener{
            val intent = Intent( this, GameActivity::class.java )
            startActivity(intent)
        }

        //LastCheak遷移
        binding.attackbutton2.setOnClickListener {
            val intent = Intent(this, testLastCheckActivity::class.java)
            startActivity(intent)
        }

//            var enemyForce: Map<String, Int> = mapOf(
//                "siga" to 1780,
//                "kyoto" to 2610,
//                "osaka" to 8830,
//                "hyougo" to 5530,
//                "nara" to 1360,
//                "wakayama" to 960,
//                "mie" to 1410
//            )

        var attackken = findViewById<TextView>(R.id.enemie)
        var hyourou = findViewById<TextView>(R.id.testhei)

        binding.sigaBt.setOnClickListener{
            attackken.setText(R.string.siga)
//            var heiken = enemyForce["siga"]



        }

        binding.kyotoBt.setOnClickListener{
            attackken.setText(R.string.kyoto)
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }

        binding.osakaBt.setOnClickListener {
            attackken.setText(R.string.osaka)
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }


        binding.hyougoBt.setOnClickListener{
            attackken.setText(R.string.hyougo)
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }

        binding.naraBt.setOnClickListener{
            attackken.setText(R.string.nara)
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }

        binding.mieBt.setOnClickListener{
            attackken.setText(R.string.mie)
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
    }
    override fun onResume() {
        super.onResume()
        soundPool =
            SoundPool.Builder().run {
                val audioAttributes = AudioAttributes.Builder().run {
                    setUsage(AudioAttributes.USAGE_GAME)
                    build()
                }
                setMaxStreams(1)
                setAudioAttributes(audioAttributes)
                build()
            }
        player = MediaPlayer.create(this, R.raw.sora)
        soundResId = soundPool.load(this, R.raw.change, 1)
    }
    override fun onPause() {
        super.onPause()
        soundPool.release()
    }
}
