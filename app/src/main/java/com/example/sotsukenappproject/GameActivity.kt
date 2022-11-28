package com.example.sotsukenappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.sotsukenappproject.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGameBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        /* ↓フッターメニュー */
        // 設定ボタン
        binding.settingButton.setOnClickListener {
            val intent = Intent( this, SettingActivity::class.java )
            startActivity(intent)
        }
        // 実績ボタン
        binding.achievementButton.setOnClickListener {
            val intent = Intent( this, AchievementActivity::class.java )
            startActivity(intent)
        }
        // 育成ボタン
        binding.canpButton.setOnClickListener {
            val intent = Intent( this, CampActivity::class.java )
            startActivity(intent)
        }
        /* ↑フッターメニュー */

        /* ↓進行開始ボタン */
        binding.attackbutton.setOnClickListener {
            val intent = Intent( this, ChoosePrefectureActivity::class.java)
            startActivity(intent)
                /* ↓進行先選択 */
                /* ChoosePrefectureActivityに記述 */
                /* ↑進行先選択 */
        }
        /* ↑進行開始ボタン */

    }

}