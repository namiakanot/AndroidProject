package com.example.sotsukenappproject

import android.R
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import android.widget.VideoView
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.FragmentManager
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

        val timer = AttackTimer((times).toLong(), 100)

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
        val fragmentManager: FragmentManager = supportFragmentManager



        override fun onTick(millisUntilFinished: Long) {
            val hour = millisUntilFinished / 1000L / 60L / 60L
            val minute = millisUntilFinished / 1000L / 60L % 60L
            val second = millisUntilFinished / 1000L % 60L
            binding.standByTimer.text = "%1d:%2$02d:%3$3d".format(hour,minute, second)
        }

        override fun onFinish() {
            binding.standByTimer.text = "0:00"



        }
    }
}