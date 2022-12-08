package com.example.sotsukenappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.FragmentManager
import com.example.sotsukenappproject.databinding.ActivityCampStandByBinding

class CampStandByActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCampStandByBinding



    //カウントダウン処理>>
    inner class CampTimer(millisInFuture: Long , countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {
        var isRunning = false

        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished/1000L/60L
            val second = millisUntilFinished/1000L%60L
            binding.standByTimer.text = "%1d:%2$02d".format(minute, second)
        }

        override fun onFinish() {
            binding.standByTimer.text = "0:00"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCampStandByBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var times = intent.getIntExtra("CampLevel",0)

        val fragmentManager: FragmentManager = supportFragmentManager

        binding.standByTimer.text = "${times}：00"
        val timer = CampTimer((times * 60 * 1000).toLong(),100)

        binding.timerStart.setOnClickListener{
            timer.start()
            binding.timerStart.isClickable = false
        }

        binding.timerStop.setOnClickListener{
            val dialog = checkpopFragment()
            fragmentManager.run{
                dialog.show(this,"")
            }
            }
        }
}








