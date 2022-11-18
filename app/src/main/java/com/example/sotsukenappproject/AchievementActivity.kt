package com.example.sotsukenappproject

import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.sotsukenappproject.databinding.ActivityAchievementBinding

class AchievementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAchievementBinding
    private lateinit var soundPool: SoundPool
    private var soundResId = 0
    private var soundResId2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAchievementBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        // 戻るを押すとメイン画面(戦闘画面)へ遷移
        binding.backbutton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            soundPool.play(soundResId2, 1.0f, 100f, 0, 0, 1.0f)
        }

        //実績
        binding.gold1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("スマせん者")
                .setMessage("ゲームを一回クリアする")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        binding.silver1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("戦いは数")
                .setMessage("100万人の差をつけて勝利する")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        binding.silver2.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("禁忌のドンメル")
                .setMessage("近畿地方をすべて制圧する")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        binding.bronze1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("明け方の猛者達")
                .setMessage("一回の進行時間が120分以上")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        binding.bronze2.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("精鋭")
                .setMessage("自軍の兵力が敵軍の勢力を下回った状態で進行達成")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        binding.bronze1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("鬼教官")
                .setMessage("大育成を一度終える")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

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
        soundResId = soundPool.load(this, R.raw.tap, 1)
        soundResId2 = soundPool.load(this, R.raw.change, 1)
    }

    override fun onPause() {
        super.onPause()
        soundPool.release()
    }
}