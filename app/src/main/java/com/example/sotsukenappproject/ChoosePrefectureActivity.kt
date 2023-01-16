package com.example.sotsukenappproject

import android.annotation.SuppressLint
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.preference.PreferenceManager
import com.example.sotsukenappproject.databinding.ActivityChoosePrefectureBinding

class ChoosePrefectureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChoosePrefectureBinding
    private lateinit var player: MediaPlayer

    private lateinit var soundPool: SoundPool
    private var soundResId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChoosePrefectureBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val nextPref = pref.getInt("WON_COUNT",0)

        changeColor(nextPref)

        //GAME遷移
        binding.backbutton2.setOnClickListener{
            val intent = Intent( this, GameActivity::class.java )
            startActivity(intent)
        }

        //LastCheak遷移
        binding.attackbutton2.setOnClickListener {
            val intent = Intent(this, LastCheckActivity::class.java)
            startActivity(intent)
        }

        val attackken = findViewById<TextView>(R.id.enemie)

        binding.naraBt.setOnClickListener{
            attackken.setText(R.string.nara)
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        binding.mieBt.setOnClickListener{
            attackken.setText(R.string.mie)
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        binding.sigaBt.setOnClickListener{
            attackken.setText(R.string.siga)
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        binding.kyotoBt.setOnClickListener{
            attackken.setText(R.string.kyoto)
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        binding.hyougoBt.setOnClickListener{
            attackken.setText(R.string.hyougo)
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        binding.osakaBt.setOnClickListener {
            attackken.setText(R.string.osaka)
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

    @SuppressLint("ResourceAsColor")
    private fun changeColor(nextPref: Int) {
        if (nextPref == 0) {
            binding.wakayamaBt.setTextColor(R.color.light_blue_600)
            binding.naraBt.setTextColor(R.color.black)
        } else if (nextPref == 1){
            binding.naraBt.setTextColor(R.color.light_blue_600)
            binding.mieBt.setTextColor(R.color.black)
        }else if (nextPref == 2){
            binding.mieBt.setTextColor(R.color.light_blue_600)
            binding.sigaBt.setTextColor(R.color.black)
        }else if (nextPref == 3){
            binding.sigaBt.setTextColor(R.color.light_blue_600)
            binding.kyotoBt.setTextColor(R.color.black)
        }else if (nextPref == 4){
            binding.kyotoBt.setTextColor(R.color.light_blue_600)
            binding.hyougoBt.setTextColor(R.color.black)
        }else if (nextPref == 5){
            binding.hyougoBt.setTextColor(R.color.light_blue_600)
            binding.osakaBt.setTextColor(R.color.black)
        }else if (nextPref == 6){
            binding.naraBt.setTextColor(R.color.light_blue_600)
            binding.mieBt.setTextColor(R.color.black)
        }
    }
}
