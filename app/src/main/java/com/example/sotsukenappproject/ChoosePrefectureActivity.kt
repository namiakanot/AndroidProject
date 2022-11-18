package com.example.sotsukenappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sotsukenappproject.databinding.ActivityChoosePrefectureBinding

class ChoosePrefectureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChoosePrefectureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChoosePrefectureBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        val myForce: Int = intent.getIntExtra("MY_FORCE", 0)

        /* 進行先の地図を押す */
        /* 今回は近畿地方のみだが、全国に広げる場合は地方選択からこのActivityに移動する。 */
        binding.osaka.setOnClickListener { onPrefectureButtonTapped("osaka", myForce) }
        binding.kyoto.setOnClickListener { onPrefectureButtonTapped("kyoto", myForce) }
        binding.nara.setOnClickListener { onPrefectureButtonTapped("nara", myForce) }
        binding.hyogo.setOnClickListener { onPrefectureButtonTapped("hyogo", myForce) }
        binding.wakayama.setOnClickListener { onPrefectureButtonTapped("wakayama", myForce) }
        binding.mie.setOnClickListener { onPrefectureButtonTapped("mie", myForce) }
        binding.siga.setOnClickListener { onPrefectureButtonTapped("siga", myForce) }

        /* 戻るボタン */
        binding.backButton.setOnClickListener{
            val intent = Intent( this, GameActivity::class.java )
            startActivity(intent)
        }
    }

    /* 最終確認画面へ */
    private fun onPrefectureButtonTapped(prefName: String, myForce: Int){
        val intent = Intent( this, LastCheckActivity::class.java )
        startActivity(intent)
        // LastCheckActivityに選択した都道府県名を渡す
        intent.putExtra("PREF_NAME", prefName)
        intent.putExtra("MY_FORCE", myForce)
    }
}