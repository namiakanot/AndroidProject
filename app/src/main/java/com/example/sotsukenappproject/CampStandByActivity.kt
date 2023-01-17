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


    //カウントダウン処理>>
    inner class CampTimer(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {

        val fragmentManager: FragmentManager = supportFragmentManager

        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished / 1000L / 60L
            val second = millisUntilFinished / 1000L % 60L
            binding.standByTimer.text = "%1d:%2$02d".format(minute, second)
        }

        override fun onFinish() {
            binding.standByTimer.text = "0:00"

            //タイマー終了後のポップアップ
            val dialog = Camp_popFragment()
            fragmentManager.run {
                dialog.show(this, "")
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCampStandByBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        val largeCampCount = pref.getInt("LCAMP_COUNT",0)

        val times = intent.getIntExtra("CampLevel", 0)
        pref.edit().putInt("SAVE_CAMP_TIME",times)
            .apply()
        if (times >= 30){
            editor.putInt("LCAMP_COUNT",largeCampCount + 1)
        }
        editor.apply()

        val fragmentManager: FragmentManager = supportFragmentManager

        binding.standByTimer.text = "${times}：00"
        val timer = CampTimer((times * 60 * 1000).toLong(), 100)

        binding.timerStart.setOnClickListener {
            timer.start()
            binding.timerStart.isClickable = false
        }

        binding.timerStop.setOnClickListener {
            //途中終了時のポップアップ
            val dialog = CheckPopFragment()
            fragmentManager.run {
                dialog.show(this, "")
            }
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        startActivity(Intent(this, GameActivity::class.java))
//        Toast.makeText(getApplicationContext(), "Good bye!" , Toast.LENGTH_SHORT).show();
    }

}








