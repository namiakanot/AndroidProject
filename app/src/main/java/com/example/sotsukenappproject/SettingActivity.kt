package com.example.sotsukenappproject

import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.media.MediaPlayer
import android.widget.SeekBar
import com.example.sotsukenappproject.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    //bgm
    private lateinit var player: MediaPlayer
    //SE
    private lateinit var soundPool: SoundPool
    private var soundResId = 0
    private var soundResId2 = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        //
        val audioManager: AudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        // 戻るを押すとメイン画面(戦闘画面)へ遷移
        binding.backBt.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }

        binding.title1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 現在の音量
        val getVolume: Int = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

        // 最大値
        val maxVolume: Int = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

        val volumePercentage: Int = getVolume / maxVolume

        binding.volume.progress = volumePercentage


        // SeekBar調節
        binding.volume.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    volume: SeekBar, progress: Int, fromUser: Boolean
                ) {
                    val str: String = getString(R.string.percentage, progress)
                    binding.textvolume.text = str
                    //
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolume*progress/100,0)
                }

                override fun onStartTrackingTouch(volume_bar: SeekBar) {
                    soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
                    setVolumeControlStream(AudioManager.STREAM_MUSIC)
                }

                override fun onStopTrackingTouch(volume_bar: SeekBar) {
                }
            }
        )
        player = MediaPlayer.create(this, R.raw.sora)
        player.isLooping = true
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
            soundResId = soundPool.load(this, R.raw.change, 1)
            soundResId2 = soundPool.load(this, R.raw.change2,1)
        }

    override fun onPause() {
        super.onPause()
        soundPool.release()
    }
}
