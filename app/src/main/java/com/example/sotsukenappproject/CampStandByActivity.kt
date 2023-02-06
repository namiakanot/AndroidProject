package com.example.sotsukenappproject

import android.R
import android.content.Intent
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.preference.PreferenceManager
import com.example.sotsukenappproject.databinding.ActivityCampStandByBinding
import android.os.Bundle as Bundle1


class CampStandByActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCampStandByBinding

    //カウントダウン処理>>
    inner class CampTimer(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {

        private val fragmentManager: FragmentManager = supportFragmentManager

        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished / 1000L / 60L
            val second = millisUntilFinished / 1000L % 60L
            binding.standByTimer.text = "%1d:%2$02d".format(minute, second)
        }

        override fun onFinish() {
            binding.standByTimer.text = "0:00"
            
            val pref = PreferenceManager.getDefaultSharedPreferences(this@CampStandByActivity)
            val editor = pref.edit()
            //タイマー終了後のポップアップ
            val dialog = Camp_popFragment()
            fragmentManager.run {
                dialog.show(this, "")
            }
            
            val userForce = pref.getInt("USER_FORCE",960)
            val times = intent.getIntExtra("CampLevel", 0)
            val largeCampCount = pref.getInt("LCAMP_COUNT",0)

            if (times >= 30){
                editor.putInt("LCAMP_COUNT",largeCampCount + 1)
                editor.putInt("USER_FORCE",userForce + 120)
            }else if( (times < 30)&&(times >= 20) ){
                editor.putInt("USER_FORCE",userForce + 50)
            }else if(times < 20){
                editor.putInt("USER_FORCE",userForce + 20)
            }
            editor.apply()

        }

    }
    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCampStandByBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val totalCampTime = pref.getInt("total_CAMPTIME",0) //総育成時間
        val forceUp = pref.getInt("force_UP",20)       //上昇値

        val times = intent.getIntExtra("CampLevel", 0)
        pref.edit().putInt("SAVE_CAMP_TIME",times)
            .putInt("total_CAMPTIME",totalCampTime + times * 60)
            .apply()

        val fragmentManager: FragmentManager = supportFragmentManager

        val strforce = forceUp.toString()
        
        Bundle1().putInt("force_UP",forceUp)
        val Fragment = Camp_popFragment()
        Fragment.arguments = Bundle1()

        binding.textView32.setText(strforce) //テスト用

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
    }
}








