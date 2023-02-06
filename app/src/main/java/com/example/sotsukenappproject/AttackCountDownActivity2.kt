package com.example.sotsukenappproject

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.example.sotsukenappproject.databinding.ActivityAttackCountDown2Binding

class AttackCountDownActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityAttackCountDown2Binding

    var dialog = Attack_popFragment()
    val fragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAttackCountDown2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //45は発表用
        val times = intent.getLongExtra("ATTACK_TIME",0)
        val hour: Long = times / 1000L / 60L / 60L
        val minute: Long = times / 1000L / 60L % 60L
        val second: Long = times / 1000L % 60L
        binding.standByTimer.text = "%1d:%2$02d:%3$02d".format(hour, minute, second)

        val timer = AttackTimer(times, 100)

        binding.timerStart.setOnClickListener {
            timer.start()
        }
        binding.timerStop.setOnClickListener {
            val dialog = checkpopFragment2()
            fragmentManager.run {
                dialog.show(this, "")
            }
        }
    }


    //カウントダウン処理>>
    inner class AttackTimer(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {

        var dialog = Attack_popFragment()
        val fragmentManager: FragmentManager = supportFragmentManager

        override fun onTick(millisUntilFinished: Long) {
            val hour = millisUntilFinished / 1000L / 60L / 60L
            val minute = millisUntilFinished / 1000L / 60L % 60L
            val second = millisUntilFinished / 1000L % 60L
            binding.standByTimer.text = "%1d:%2$02d:%3$3d".format(hour,minute, second)
        }

        override fun onFinish() {
            binding.standByTimer.text = "0:00"

            fragmentManager.run{
                dialog.show(this,"")
            }

            val pref = PreferenceManager.getDefaultSharedPreferences(this@AttackCountDownActivity2)
            val times = intent.getLongExtra("ATTACK_TIME",0).toInt()
            val userForce = pref.getInt("USER_FORCE",960)
            val wonCount = pref.getInt("WON_COUNT", 0)
            val lostCount = pref.getInt("LOST_COUNT",0)
            val losingCount = pref.getInt("LOSING_COUNT",0)
            val totalAttackTime = pref.getInt("TOTAL_ATTACK_TIME",0)
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
                .putInt("TOTAL_ATTACK_TIME",totalAttackTime + times / 1000)
                .apply()
        }
    }
}