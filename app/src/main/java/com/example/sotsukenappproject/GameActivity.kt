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

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val userForce = pref.getInt("USER_FORCE", 960)
        val gameClearCount = pref.getInt("GAME_CLEAR_COUNT", 0)
        val kinkiAttackedCount = pref.getInt("KINKI_ATTACKED_COUNT", 0)
        val largeCampCount = pref.getInt("LARGE_COUNT", 0)
        val middleCampCount = pref.getInt("MIDDLE_COUNT", 0)
        val smallCampCount = pref.getInt("SMALL_COUNT", 0)

        // MainActivity から
        // val userName = intent.getStringExtra("USER_NAME")
        // AttackCountDownActivity から
        val attackTime = intent.getIntExtra("ATTACK_TIME", 0)

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
            intent.putExtra("USER_FORCE", userForce)
            intent.putExtra("LARGE_COUNT", largeCampCount)
            intent.putExtra("MIDDLE_COUNT", middleCampCount)
            intent.putExtra("SMALL_COUNT", smallCampCount)
            startActivity(intent)
        }
        /* ↑フッターメニュー */

        /* ↓進行開始ボタン */
        binding.attackbutton.setOnClickListener {
            val intent = Intent( this, ChoosePrefectureActivity::class.java)
        //    intent.putExtra("USER_NAME", userName)
            intent.putExtra("USER_FORCE", userForce)
            intent.putExtra("GAME_CLEAR_COUNT", gameClearCount)
            intent.putExtra("KINKI_ATTACKED_COUNT", kinkiAttackedCount)
            startActivity(intent)
                /* ↓進行先選択 */
                /* ChoosePrefectureActivityに記述 */
                /* ↑進行先選択 */
        }
        /* ↑進行開始ボタン */

        saveUserData(/*userName!!, */userForce, gameClearCount, kinkiAttackedCount, attackTime)
        saveCampCount(largeCampCount, middleCampCount, smallCampCount)
    }


    // データ保存
    @Suppress("NAME_SHADOWING")
    private fun saveUserData(/*userName: String,*/ userForce: Int, gameClearCount: Int, kinkiAttackedCount: Int, attackTime: Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        // val userName = pref.getString("USER_NAME", userName)
        val userForce = pref.getInt("USER_FORCE", userForce)
        val gameClearCount = pref.getInt("GAME_CLEAR_COUNT", gameClearCount)
        val kinkiAttackedCount = pref.getInt("KINKI_ATTACKED_COUNT", kinkiAttackedCount)
        val maxAttackTime = pref.getInt("MAX_ATTACK_TIME", attackTime)

        val edtMaxAttackTime: Int =
            when{
                maxAttackTime > attackTime -> attackTime
                else -> maxAttackTime
            }

        val edtKinkiAttackedCount: Int =
            when{
                gameClearCount >= 13 -> 2
                gameClearCount >= 6 -> 1
                else -> kinkiAttackedCount
            }

        val editor = pref.edit()
        editor//.putString("USER_NAME", userName)
            .putInt("USER_FORCE", userForce)
            .putInt("GAME_CLEAR_COUNT", gameClearCount)
            .putInt("KINKI_ATTACKED_COUNT", edtKinkiAttackedCount)
            .putInt("MAX_ATTACK_TIME", edtMaxAttackTime)
            .apply()
    }
    // データ保存2(Camp用)
    private fun saveCampCount(large: Int, middle: Int, small: Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val largeCount = pref.getInt("LARGE_COUNT", large)
        val middleCount = pref.getInt("MIDDLE_COUNT", middle)
        val smallCount = pref.getInt("SMALL_COUNT", small)

        val editor = pref.edit()
        editor.putInt("LARGE_COUNT", largeCount)
            .putInt("MIDDLE_COUNT", middleCount)
            .putInt("SMALL_COUNT", smallCount)
            .apply()
    }
}