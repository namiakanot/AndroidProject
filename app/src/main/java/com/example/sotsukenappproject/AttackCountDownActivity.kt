package com.example.sotsukenappproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.CountDownTimer
import com.example.sotsukenappproject.databinding.ActivityAttackCountDownBinding


class AttackCountDownActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAttackCountDownBinding

    //カウントダウン処理>>
    inner class CampTimer(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {
        var isRunning = false

        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished / 1000L / 60L
            val second = millisUntilFinished / 1000L % 60L
            binding.standByTimer.text = "%1d:%2$02d".format(minute, second)
        }

        override fun onFinish() {
            binding.standByTimer.text = "0:00"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        /* 内容未確定 */
        binding = ActivityAttackCountDownBinding.inflate(layoutInflater)
        setContentView(binding.root)

            var times = 0 //intent.getIntExtra("",0)   "タイマーの取得"


            binding.standByTimer.text = "${times}：00"
            val timer = CampTimer((times * 60 * 1000).toLong(), 100)
            binding.timerStart.setOnClickListener {
                timer.start()
            }
            binding.timerStop.setOnClickListener {
                timer.cancel()
            }
        }
    }

