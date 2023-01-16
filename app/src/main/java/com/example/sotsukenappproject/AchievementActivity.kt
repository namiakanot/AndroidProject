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
import kotlin.math.max
import androidx.preference.PreferenceManager

class AchievementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAchievementBinding
    private lateinit var soundPool: SoundPool
    private var soundResId = 0
    private var soundResId2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAchievementBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        val userForce = pref.getInt("USER_FORCE", 960)
        val enemyForce = pref.getInt("ENEMY_FORCE", 0)
        val attackedCounter = pref.getInt("WON_COUNT", 0)
        // 未設定
        val largeCampCount = pref.getInt("LCAMP_COUNT", 0)
        val attackTime = pref.getLong("ATTACK_TIME",0).toInt()

        // 戻るを押すとメイン画面(戦闘画面)へ遷移
        binding.backBt.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            soundPool.play(soundResId2, 1.0f, 100f, 0, 0, 1.0f)
        }

        //実績
        /**
         * 金実績１
         * 条件:gameClearCount >= 1 (変数はGameActivityを参照)
         */
        binding.gold1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("スマせん者")
                .setMessage("ゲームを一回クリアする")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        /**
         * 銀実績１
         * 条件:enemyForce - userForce >= 1000
         */
        binding.silver1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("戦いは数")
                .setMessage("100万人の差をつけて勝利する")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        /**
         * 銀実績２
         * 条件:attackedCounter >= 6
         */
        binding.silver2.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("禁忌のドンメル")
                .setMessage("近畿地方をすべて制圧する")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        /**
         * 銅実績１
         * 条件:maxAttackTime >= 120
         */
        binding.bronze1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("明け方の猛者達")
                .setMessage("一回の進行時間が120分以上")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        /**
         * 銅実績２
         * 条件:userForce < enemyForce
         */
        binding.bronze2.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("精鋭")
                .setMessage("自軍の兵力が敵軍の勢力を下回った状態で進行達成")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        /**
         * 銅実績３
         * 条件:largeCampCount >= 1
         */
        binding.bronze3.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("鬼教官")
                .setMessage("大育成を一度終える")
                .setPositiveButton("OK") { dialog, which -> }
                .show()

            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }

        /**
         * ここからは実績のprogressBar要素の値を変更するプログラム
         */

        /**
         * 金実績１
         */
        binding.gold1bar.max = 1
        if(attackedCounter >= 1){
            binding.gold1bar.progress = 1
        }

        /**
         * 銀実績１
         */
        binding.silver1bar.max = 1000
        val differenceForce: Int = enemyForce - userForce
        var maxDifferenceForce = pref.getInt("MAX_DIFFERENCE_FORCE",0)
        if(maxDifferenceForce >= 1000){
            maxDifferenceForce = 1000
        }else if(maxDifferenceForce < differenceForce){
            maxDifferenceForce = differenceForce
        }
        binding.silver1bar.progress = maxDifferenceForce

        editor.putInt("MAX_DIFFERENCE_FORCE",maxDifferenceForce)

        /**
         * 銀実績２
         */
        binding.silver2bar.max = 6
        if((attackedCounter >= 0)&&(attackedCounter <= 6)){
            binding.silver2bar.progress = attackedCounter
        }
        /**
         * 銅実績１
         */
        binding.bronze1bar.max = 7200 // sec.
        var maxAttackTime = pref.getInt("MAX_ATTACK_TIME",0)
        if(attackTime >= 7200){
            maxAttackTime = 7200
        }else if(attackTime > maxAttackTime){
            maxAttackTime = attackTime
        }
        binding.bronze1bar.progress = maxAttackTime

        editor.putInt("MAX_ATTACK_TIME",maxAttackTime)

        /**
         * 銅実績２
         */
        binding.bronze2bar.max = 1
        if(differenceForce < 0){
            binding.bronze2bar.progress = 1
        }

        /**
         * 銅実績３
         */
        binding.bronze3bar.max = 1
        if(largeCampCount >= 1){
            binding.bronze3bar.progress = 1
        }

        editor.apply()
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