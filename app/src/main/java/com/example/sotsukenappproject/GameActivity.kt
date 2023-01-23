package com.example.sotsukenappproject

import android.annotation.SuppressLint
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.sotsukenappproject.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    private lateinit var soundPool: SoundPool
    private var soundResId = 0
    private var soundResId2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGameBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val userForce = pref.getInt("USER_FORCE",960)
        val wonCount = pref.getInt("WON_COUNT",0)
        binding.military.text = userForce.toString()
        changeColor(wonCount)

        /* ↓フッターメニュー */
        // 設定ボタン
        binding.settingButton.setOnClickListener {
            val intent = Intent( this, SettingActivity::class.java )
            startActivity(intent)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            soundPool.play(soundResId2, 1.0f, 100f, 0, 0, 1.0f)
        }
        // 実績ボタン
        binding.achievementButton.setOnClickListener {
            val intent = Intent( this, AchievementActivity::class.java )
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            soundPool.play(soundResId2, 1.0f, 100f, 0, 0, 1.0f)
        }
        // 育成ボタン
        binding.canpButton.setOnClickListener {
            val intent = Intent( this, CampActivity::class.java )
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            soundPool.play(soundResId2, 1.0f, 100f, 0, 0, 1.0f)
        }
        /* ↑フッターメニュー */

        /* ↓進行開始ボタン */
        binding.attackbutton.setOnClickListener {
            val intent = Intent( this, ChoosePrefectureActivity::class.java)
            startActivity(intent)
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        /* ↑進行開始ボタン */

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
        soundResId = soundPool.load(this, R.raw.wadaiko, 1)
        soundResId2 = soundPool.load(this, R.raw.change,1)
    }

    override fun onPause(){
        super.onPause()
        soundPool.release()
    }

    @SuppressLint("ResourceAsColor")
    private fun changeColor(nextPref: Int) {
        when (nextPref) {
            0 -> {
                binding.nara.setImageResource(R.drawable.nara_gray)
            }
            1 -> {
                binding.nara.setImageResource(R.drawable.nara_blue)
                binding.mie.setImageResource(R.drawable.mie_gray)
            }
            2 -> {
                binding.nara.setImageResource(R.drawable.nara_blue)
                binding.mie.setImageResource(R.drawable.mie_blue)
                binding.siga.setImageResource(R.drawable.siga_gray)
            }
            3 -> {
                binding.nara.setImageResource(R.drawable.nara_blue)
                binding.mie.setImageResource(R.drawable.mie_blue)
                binding.siga.setImageResource(R.drawable.siga_blue)
                binding.kyoto.setImageResource(R.drawable.kyouto_gray)
            }
            4 -> {
                binding.nara.setImageResource(R.drawable.nara_blue)
                binding.mie.setImageResource(R.drawable.mie_blue)
                binding.siga.setImageResource(R.drawable.siga_blue)
                binding.kyoto.setImageResource(R.drawable.kyouto_blue)
                binding.hyogo.setImageResource(R.drawable.hyougo_gray)
            }
            5 -> {
                binding.nara.setImageResource(R.drawable.nara_blue)
                binding.mie.setImageResource(R.drawable.mie_blue)
                binding.siga.setImageResource(R.drawable.siga_blue)
                binding.kyoto.setImageResource(R.drawable.kyouto_blue)
                binding.hyogo.setImageResource(R.drawable.hyougo_blue)
                binding.osaka.setImageResource(R.drawable.oosaka_gray)
            }
            6 -> {
                binding.nara.setImageResource(R.drawable.nara_blue)
                binding.mie.setImageResource(R.drawable.mie_blue)
                binding.siga.setImageResource(R.drawable.siga_blue)
                binding.kyoto.setImageResource(R.drawable.kyouto_blue)
                binding.hyogo.setImageResource(R.drawable.hyougo_blue)
                binding.osaka.setImageResource(R.drawable.oosaka_blue)
            }
        }
    }

}