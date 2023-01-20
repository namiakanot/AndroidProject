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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAttackCountDownBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(binding.root)

        val times = intent.getIntExtra("ATTACK_TIME",0)
        val hour: Long = times / 1000L / 60L / 60L
        val minute: Long = times / 1000L / 60L % 60L
        val second: Long = times / 1000L % 60L
        binding.standByTimer.text = "%1$001d:%2$02d:%3$02d".format(hour, minute, second)

        val timer = AttackTimer((times * 60 * 1000).toLong(), 1000)

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
        var isRunning = false

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
            val prefForce: Array<Int> = arrayOf(1360,1460,1780,2610,5530,8830)

            pref.edit().putInt("USER_FORCE", (userForce + prefForce[wonCount] * 0.3).toInt())
                .putInt("WON_COUNT",wonCount + 1)
                .putInt("LOSING_COUNT",lostCount - 1)
                .apply()
        }
    }

}
