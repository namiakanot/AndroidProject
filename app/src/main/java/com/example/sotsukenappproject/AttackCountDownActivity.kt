package com.example.sotsukenappproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.CountDownTimer
import androidx.fragment.app.FragmentManager
import com.example.sotsukenappproject.databinding.ActivityAttackCountDownBinding


class AttackCountDownActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAttackCountDownBinding

    var dialog = Attack_popFragment()
    val fragmentManager: FragmentManager = supportFragmentManager



    //カウントダウン処理>>
    inner class CampTimer(millisInFuture: Long, countDownInterval: Long) :
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



        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        /* 内容未確定 */
        binding = ActivityAttackCountDownBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val times = intent.getIntExtra("ATTACK_TIME",0)
        val hour: Long = times / 1000L / 60L / 60L
        val minute: Long = times / 1000L / 60L % 60L
        val second: Long = times / 1000L % 60L
        binding.standByTimer.text = "所要時間：%1d:%2$02d:%3$02d".format(hour, minute, second)

        val timer = CampTimer((times * 60 * 1000).toLong(), 100)

        binding.timerStart.setOnClickListener {
            timer.start()
        }
        binding.timerStop.setOnClickListener {
            timer.cancel()
        }
    }
}
