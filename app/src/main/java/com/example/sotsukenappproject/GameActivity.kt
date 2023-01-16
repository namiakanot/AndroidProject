package com.example.sotsukenappproject

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                /* ↓進行先選択 */
                /* ChoosePrefectureActivityに記述 */
                /* ↑進行先選択 */
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
        /* ↑進行開始ボタン */

        // saveUserData(userName!!, userForce, gameClearCount, kinkiAttackedCount, attackTime)
        // saveCampCount(largeCampCount, middleCampCount, smallCampCount)

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

}