package com.example.sotsukenappproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.CountDownTimer
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.example.sotsukenappproject.databinding.ActivityAttackCountDownBinding


class AttackCountDownActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAttackCountDownBinding

    var dialog = Attack_popFragment()
    val fragmentManager: FragmentManager = supportFragmentManager



    //カウントダウン処理>>
    inner class CampTimer(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {

        var dialog = Attack_popFragment()

        override fun onTick(millisUntilFinished: Long) {
            val hour = millisUntilFinished / 1000L / 60L / 60L
            val minute = millisUntilFinished / 1000L / 60L % 60L
            val second = millisUntilFinished / 1000L % 60L
            binding.standByTimer.text = "%1d:%2$02d:%3$03d".format(hour,minute, second)
        }

        override fun onFinish() {
            binding.standByTimer.text = "0:00"

            fragmentManager.run{
                dialog.show(this,"")
            }
            val pref = PreferenceManager.getDefaultSharedPreferences(this@AttackCountDownActivity)
            val userForce = pref.getInt("USER_FORCE",960)
            val wonCount = pref.getInt("WON_COUNT", 0)
            var lostCount = pref.getInt("LOST_COUNT",0)
            lostCount += 1
            var losingCount = pref.getInt("LOSING_COUNT",0)
            losingCount += 1
            val prefForce: Array<Int> = arrayOf(1360,1460,1780,2610,5530,8830)

            var i = 0
            if(lostCount > losingCount){
                while (i < lostCount - losingCount){
                    prefForce[wonCount] *= 1.1.toInt()
                    i++
                }
            }else if(losingCount > lostCount){
                while (i < losingCount - lostCount){
                    prefForce[wonCount] *= 0.9.toInt()
                    i++
                }
            }

            pref.edit().putInt("USER_FORCE", (userForce + prefForce[wonCount] * 0.3).toInt())
                .putInt("WON_COUNT",wonCount + 1)
                .putInt("LOSING_COUNT",0)
                .apply()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        /* 内容未確定 */
        binding = ActivityAttackCountDownBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val userForce = pref.getInt("USER_FORCE",960)
        val times = intent.getIntExtra("ATTACK_TIME",0)
        val hour: Long = times / 1000L / 60L / 60L
        val minute: Long = times / 1000L / 60L % 60L
        val second: Long = times / 1000L % 60L
        binding.standByTimer.text = "%1d:%2$02d:%3$02d".format(hour, minute, second)

        val timer = CampTimer((times * 60 * 1000).toLong(), 1000)

        binding.timerStart.setOnClickListener {
            timer.start()
        }
        binding.timerStop.setOnClickListener {
            timer.cancel()
            var lostCount = pref.getInt("LOST_COUNT",0)
            lostCount += 1
            var losingCount = pref.getInt("LOSING_COUNT",0)
            losingCount += 1
            pref.edit().putInt("USER_FORCE",userForce * (1 - (0.1 * lostCount)).toInt() )
                .apply()
        }
    }
}
