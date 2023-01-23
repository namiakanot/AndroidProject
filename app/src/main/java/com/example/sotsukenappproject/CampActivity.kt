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
import androidx.preference.PreferenceManager

class CampActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCampBinding
    private lateinit var soundPool: SoundPool
    private var soundResgr = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCampBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        val userForce = pref.getInt("USER_FORCE",960)

        binding.military1.text = userForce.toString()
        val campFailedFlag = intent.getBooleanExtra("CAMP_FAILED_FLAG",false)
        val largeCampCount = pref.getInt("LCAMP_COUNT",0)
        val saveCampTime = pref.getInt("SAVE_CAMP_TIME",0)
        if( campFailedFlag && (saveCampTime >= 30) ){
            pref.edit().putInt("LCAMP_COUNT",largeCampCount - 1)
                .apply()
        }

        binding.military1.text = userForce.toString()

        // 戻るを押すとメイン画面(戦闘画面)へ遷移
        binding.backbutton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }

        val textMessage = binding.syotyouword
        val small = binding.smallGrow
        val middle = binding.middleGrow
        val large = binding.largeGrow
        val timerText = binding.timer
        var growuptimer = 0
        var forceUp:Int


        //  ボタン押したとき
        small.setOnClickListener{
            textMessage.setText(R.string.textsmall)
            timerText.text = "10:00"
            growuptimer = 10

            pref.edit().putInt("force_UP",20).apply()
            forceUp = 20
            var strforce = forceUp.toString()
            binding.forceup.setText(strforce)

//            binding.growbar.setOnSeekBarChangeListener(
//                object : SeekBar.OnSeekBarChangeListener {
//                    override fun onProgressChanged(
//                        growbar: SeekBar, progress: Int, fromUser: Boolean) {
//                        val str: String = getString(R.string.percentage, progress)
//                    }
//
//
//                    override fun onStartTrackingTouch(volume_bar: SeekBar) {
//                    }
//                    override fun onStopTrackingTouch(volume_bar: SeekBar) {
//                    }
//                }
//            )
        }

        middle.setOnClickListener{
            textMessage.setText(R.string.textmiddle)
            timerText.text = "20:00"
            growuptimer = 20

            pref.edit().putInt("force_UP",50).apply()
            forceUp = 50
            var strforce = forceUp.toString()
            binding.forceup.setText(strforce)

//            binding.growbar.setOnSeekBarChangeListener(
//                object : SeekBar.OnSeekBarChangeListener {
//                    override fun onProgressChanged(
//                        growbar: SeekBar, progress: Int, fromUser: Boolean) {
//                        val str: String = getString(R.string.percentage, progress)
//                    }
//
//                    override fun onStartTrackingTouch(volume_bar: SeekBar) {
//                        soundPool.play(soundResgr, 1.0f, 100f, 0, 0, 1.0f)
//                    }
//                    override fun onStopTrackingTouch(volume_bar: SeekBar) {
//                    }
//                }
//            )
        }

        large.setOnClickListener{
            textMessage.setText(R.string.textlarge)
            timerText.text = "30:00"
            growuptimer = 30

            pref.edit().putInt("force_UP",120).apply()
            forceUp = 120
            val strforce = forceUp.toString()
            binding.forceup.setText(strforce)


//            binding.growbar.setOnSeekBarChangeListener(
//                object : SeekBar.OnSeekBarChangeListener {
//                    override fun onProgressChanged(
//                        growbar: SeekBar, progress: Int, fromUser: Boolean) {
//                        val str: String = getString(R.string.percentage, progress)
//                    }
//                    override fun onStartTrackingTouch(volume_bar: SeekBar) {
//                        soundPool.play(soundResgr, 1.0f, 100f, 0, 0, 1.0f)
//                    }
//                    override fun onStopTrackingTouch(volume_bar: SeekBar) {
//                    }
//                }
//            )
        }
        //育成開始ボタン　
        binding.growbutton.setOnClickListener {
            val intent = Intent(this, CampStandByActivity::class.java)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            intent.putExtra("CampLevel", growuptimer)
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
