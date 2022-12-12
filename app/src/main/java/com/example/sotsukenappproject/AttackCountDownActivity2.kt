package com.example.sotsukenappproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.CountDownTimer
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
//        setContentView(binding.root)

        var times = 0 //intent.getIntExtra("",0)   "タイマーの取得"

        binding.standByTimer.text = "${times}：00"
        val timer = AttackTimer((times * 60 * 1000).toLong(), 100)

        binding.timerStart.setOnClickListener {
            timer.start()

//            fun startVideo(){
//                attackVideo.postDelayed({
//                    videoView.setBackGroundresouce(R.color.bland_yellow)
//                },VideoView.duration.toLong() - 100)
//                videoView.start()
//                Handler().postDelayed({
//                    videoView.setBackgroundResource(R.color.bland_clear)
//                })
//            }
        }
        binding.timerStop.setOnClickListener {
            timer.cancel()
        }
    }


    //カウントダウン処理>>
    inner class AttackTimer(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {
        var isRunning = false

        var dialog = Attack_popFragment()
        val fragmentManager: FragmentManager = supportFragmentManager



        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished / 1000L / 60L
            val second = millisUntilFinished / 1000L % 60L
            binding.standByTimer.text = "%1d:%2$02d".format(minute, second)
        }

        override fun onFinish() {
            binding.standByTimer.text = "0:00"

//            fragmentManager?.run{
//                dialog.show(this,)
//            }



        }
    }
}