package com.example.sotsukenappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.sotsukenappproject.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        // 戻るを押すとメイン画面(戦闘画面)へ遷移
        binding.backButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }


        // 初期値
        binding.volume.setProgress(0)

        //　最大値
        binding.volume.setMax(100)

        // SeekBar調節
        binding.volume.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    volume: SeekBar, progress: Int, fromUser: Boolean) {
                    val str: String = getString(R.string.percentage, progress)
                    binding.textvolume.text = str
                }
                override fun onStartTrackingTouch(volume_bar: SeekBar) {
                }
                override fun onStopTrackingTouch(volume_bar: SeekBar) {
                }
            }
        )


    }
}