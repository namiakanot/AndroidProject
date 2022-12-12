package com.example.sotsukenappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.example.sotsukenappproject.databinding.ActivityTestchooseprefectureBinding

class testchooseprefectureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestchooseprefectureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTestchooseprefectureBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        //GAME遷移
        binding.backbutton2.setOnClickListener{
            val intent = Intent( this, GameActivity::class.java )
            startActivity(intent)
        }

        //LastCheak遷移
        binding.attackbutton2.setOnClickListener {
            val intent = Intent(this, testLastCheckActivity::class.java)
            startActivity(intent)
        }

//            var enemyForce: Map<String, Int> = mapOf(
//                "siga" to 1780,
//                "kyoto" to 2610,
//                "osaka" to 8830,
//                "hyougo" to 5530,
//                "nara" to 1360,
//                "wakayama" to 960,
//                "mie" to 1410
//            )

        var attackken = findViewById<TextView>(R.id.enemie)
        var hyourou = findViewById<TextView>(R.id.testhei)

        binding.sigaBt.setOnClickListener{
            attackken.setText(R.string.siga)
//            var heiken = enemyForce["siga"]



        }

        binding.kyotoBt.setOnClickListener{
            attackken.setText(R.string.kyoto)
        }

        binding.osakaBt.setOnClickListener {
            attackken.setText(R.string.osaka)
        }


        binding.hyougoBt.setOnClickListener{
            attackken.setText(R.string.hyougo)
        }

        binding.naraBt.setOnClickListener{
            attackken.setText(R.string.nara)
        }

        binding.wakayamaBt.setOnClickListener{
            attackken.setText(R.string.wakayama)
        }

        binding.mieBt.setOnClickListener{
            attackken.setText(R.string.mie)
        }
    }
}
