package com.example.sotsukenappproject

import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.sotsukenappproject.databinding.ActivityCampBinding

class CampActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCampBinding
    private lateinit var soundPool: SoundPool
    private var soundResgr = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCampBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 戻るを押すとメイン画面(戦闘画面)へ遷移
        binding.backbutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var textMessage = findViewById<TextView>(R.id.syotyouword)
        var small = findViewById<ImageButton>(R.id.small_grow)
        var middle = findViewById<ImageButton>(R.id.middle_grow)
        var large = findViewById<ImageButton>(R.id.large_grow)
        var timerText = findViewById<TextView>(R.id.timer)
        var growuptimer : Int = 0


        //  ボタン押したとき
        small.setOnClickListener{
            textMessage.setText(R.string.textsmall)
            timerText.setText("10:00")
            growuptimer = 10


            binding.growbar.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        growbar: SeekBar, progress: Int, fromUser: Boolean) {
                        val str: String = getString(R.string.percentage, progress)
                    }


                    override fun onStartTrackingTouch(volume_bar: SeekBar) {
                        soundPool.play(soundResgr, 1.0f, 100f, 0, 0, 1.0f)
                    }
                    override fun onStopTrackingTouch(volume_bar: SeekBar) {
                    }
                }
            )
        }

        middle.setOnClickListener{
            textMessage.setText(R.string.textmiddle)
            timerText.setText("20:00")
            growuptimer = 20
            binding.growbar.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        growbar: SeekBar, progress: Int, fromUser: Boolean) {
                        val str: String = getString(R.string.percentage, progress)
                    }

                    override fun onStartTrackingTouch(volume_bar: SeekBar) {
                        soundPool.play(soundResgr, 1.0f, 100f, 0, 0, 1.0f)
                    }
                    override fun onStopTrackingTouch(volume_bar: SeekBar) {
                    }
                }
            )
        }

        large.setOnClickListener{
            textMessage.setText(R.string.textlarge)
            timerText.setText("30:00")
            growuptimer = 30
            binding.growbar.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        growbar: SeekBar, progress: Int, fromUser: Boolean) {
                        val str: String = getString(R.string.percentage, progress)

                    }

                    override fun onStartTrackingTouch(volume_bar: SeekBar) {
                        soundPool.play(soundResgr, 1.0f, 100f, 0, 0, 1.0f)
                    }
                    override fun onStopTrackingTouch(volume_bar: SeekBar) {
                    }
                }
            )
        }
        //育成開始ボタン　
        binding.growbutton.setOnClickListener {
            val intent = Intent(this, CampStandByActivity::class.java)

            //
            intent.putExtra("CampLevel",growuptimer.toInt())

            startActivity(intent)

        }

    }

    override fun onResume() {
        super.onResume()
        soundPool =
            SoundPool.Builder().run {
                val audioAttributes = AudioAttributes.Builder().run {
                    setUsage(AudioAttributes.USAGE_GAME)
                    build()
                }
                setMaxStreams(1)
                setAudioAttributes(audioAttributes)
                build()
            }
        soundResgr = soundPool.load(this, R.raw.set, 1)
    }
    override fun onPause() {
        super.onPause()
        soundPool.release()
    }

}
