package com.example.sotsukenappproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.AudioAttributes
import android.media.SoundPool
import android.widget.SeekBar
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.sotsukenappproject.databinding.ActivityCanpBinding

class CanpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCanpBinding
    private lateinit var soundPool: SoundPool
    private var soundResgr = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCanpBinding.inflate(layoutInflater)
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

        //  ボタン押したとき
        small.setOnClickListener{
            textMessage.setText(R.string.textsmall)
            binding.growbar.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        growbar: SeekBar, progress: Int, fromUser: Boolean) {
                        val str: String = getString(R.string.percentage, progress)
                        binding.timer.text = str
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
            binding.growbar.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        growbar: SeekBar, progress: Int, fromUser: Boolean) {
                        val str: String = getString(R.string.percentage, progress)
                        binding.timer.text = str
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
            binding.growbar.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        growbar: SeekBar, progress: Int, fromUser: Boolean) {
                        val str: String = getString(R.string.percentage, progress)
                        binding.timer.text = str
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
            val intent = Intent(this, Canp_Stand_ByActivity::class.java)
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
