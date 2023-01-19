package com.example.sotsukenappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.sotsukenappproject.databinding.ActivityCheatModeBinding

class CheatModeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatModeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCheatModeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        // 変更できる値一覧
        val userForce = pref.getInt("USER_FORCE", 960)
        val wonCount = pref.getInt("WON_COUNT", 0)
        val lostCount = pref.getInt("LOST_COUNT", 0)
        val losingCount = pref.getInt("LOSING_COUNT", 0)
        val largeCampCount = pref.getInt("LCAMP_COUNT", 0)

        var editDataButtonCount = pref.getInt("CHEAT_COUNT",0)

        if(editDataButtonCount == 0){
            editor.putInt("SAVE_USER_FORCE", userForce)
                .putInt("SAVE_WON_COUNT", wonCount)
                .putInt("SAVE_LOST_COUNT", lostCount)
                .putInt("SAVE_LOSING_COUNT", losingCount)
                .putInt("SAVE_LCAMP_COUNT", largeCampCount)
                .apply()
        }

        /**
         * 現状の一覧を表示させる
         */
        binding.userForce.text = userForce.toString()
        binding.wonCount.text = wonCount.toString()
        binding.lostCount.text = lostCount.toString()
        binding.losingCount.text = losingCount.toString()
        binding.largeCampCount.text = largeCampCount.toString()

        /**
         * 変更ボタンを押すとそれぞれにEditTextを追加し、その値を読み取る
         */
        binding.editDataButton.setOnClickListener() {
            val edtUserForce: Int = binding.editUserForce.text.toInt()
            val edtWonCount: Int = binding.editWonCount.text.toInt()
            val edtLostCount: Int = binding.editLostCount.text.toInt()
            val edtLosingCount: Int = binding.editLosingCount.text.toInt()
            val edtLcampCount: Int = binding.editLcampCount.text.toInt()

            editor.putInt("USER_FORCE", edtUserForce)
               .putInt("WON_COUNT", edtWonCount)
               .putInt("LOST_COUNT", edtLostCount)
               .putInt("LOSING_COUNT", edtLosingCount)
               .putInt("LCAMP_COUNT", edtLcampCount)
               .putInt("CHEAT_COUNT", editDataButtonCount + 1)
               .apply()

            startActivity(Intent(this,MainActivity::class.java))
        }

        /**
         * 元に戻す(チートモード使用前に戻す)
         */

        binding.resetButton.setOnClickListener(){

            val saveUserForce = pref.getInt("SAVE_USER_FORCE", 960)
            val saveWonCount = pref.getInt("SAVE_WON_COUNT", 0)
            val saveLostCount = pref.getInt("SAVE_LOST_COUNT", 0)
            val saveLosingCount = pref.getInt("SAVE_LOSING_COUNT", 0)
            val saveLcampCount = pref.getInt("SAVE_LCAMP_COUNT", 0)

            editor.putInt("USER_FORCE", saveUserForce)
                .putInt("WON_COUNT", saveWonCount)
                .putInt("LOST_COUNT", saveLostCount)
                .putInt("LOSING_COUNT", saveLosingCount)
                .putInt("LCAMP_COUNT", saveLcampCount)
                .putInt("CHEAT_COUNT",0)
                .apply()

            startActivity(Intent(this,MainActivity::class.java))
        }


    }
}