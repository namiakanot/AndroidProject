package com.example.sotsukenappproject

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.example.sotsukenappproject.databinding.ActivityCampStandByBinding

class CampStandByActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCampStandByBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCampStandByBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        //外にもっていく数値
        val userForce = pref.getInt("USER_FORCE",960)       //兵力
        val campCount = pref.getInt("camp_COUNT",0)         //総育成回数
        val totalCampTime = pref.getInt("total_CAMPTIME",0) //総育成時間
        val forceUp = intent.getIntExtra("force_UP",20)       //上昇値

        //Activity内で完結する数値
        val oldUserForce: String = userForce.toString()
        val newUserForce = userForce + forceUp

        val editor = pref.edit()
        val largeCampCount = pref.getInt("LCAMP_COUNT",0)

        val times = intent.getIntExtra("CampLevel", 0)
        if (times == 30){
            editor.putInt("LCAMP_COUNT",largeCampCount + 1)
        }
        editor.apply()

        val fragmentManager: FragmentManager = supportFragmentManager

        val strforce = forceUp.toString()
        binding.textView32.setText(strforce) //テスト用

        binding.standByTimer.text = "${times}：00"
        val timer = CampTimer((times * 60 * 1000).toLong(), 100)

        binding.timerStart.setOnClickListener {
            timer.start()
            binding.timerStart.isClickable = false
        }

        binding.timerStop.setOnClickListener {
            //途中終了時のポップアップ
            val dialog = checkpopFragment()
            fragmentManager.run {
                dialog.show(this, "")
            }
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        startActivity(Intent(this, GameActivity::class.java))
    }

    //カウントダウン処理>>
    inner class CampTimer(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {
        var isRunning = false

        val fragmentManager: FragmentManager = supportFragmentManager

        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished / 1000L / 60L
            val second = millisUntilFinished / 1000L % 60L
            binding.standByTimer.text = "%1d:%2$02d".format(minute, second)
        }

        override fun onFinish(){
            binding.standByTimer.text = "0:00"
            //タイマー終了後のポップアップ
            val dialog = Camp_popFragment()
            fragmentManager.run {
                dialog.show(this, "")
            }

        }

    }

}








