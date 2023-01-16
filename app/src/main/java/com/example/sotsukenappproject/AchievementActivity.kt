package com.example.sotsukenappproject

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAchievementBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 戻るを押すとメイン画面(戦闘画面)へ遷移
        binding.backBt.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }

        //実績
        /**
         * 条件:gameClearCount >= 1 (変数はGameActivityを参照)
         */
        binding.gold1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("スマせん者")
                .setMessage("ゲームを一回クリアする")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

        }
        /**
         * 条件:enemyForce - userForce >= 100000
         */
        binding.silver1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("戦いは数")
                .setMessage("100万人の差をつけて勝利する")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

        }
        /**
         * 条件:kinkiAttackedCount >= 1
         */
        binding.silver2.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("禁忌のドンメル")
                .setMessage("近畿地方をすべて制圧する")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

        }
        /**
         * 条件:maxAttackTime >= 120
         */
        binding.bronze1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("明け方の猛者達")
                .setMessage("一回の進行時間が120分以上")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

        }
        /**
         * 条件:userForce < enemyForce
         */
        binding.bronze2.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("精鋭")
                .setMessage("自軍の兵力が敵軍の勢力を下回った状態で進行達成")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

        }
        /**
         * 条件:largeCampCount >= 1
         */
        binding.bronze1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("鬼教官")
                .setMessage("大育成を一度終える")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

        }
    }
}