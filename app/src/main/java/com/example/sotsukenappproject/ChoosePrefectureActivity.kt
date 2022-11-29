package com.example.sotsukenappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.sotsukenappproject.databinding.ActivityChoosePrefectureBinding

class ChoosePrefectureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChoosePrefectureBinding

    var enemylevel : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChoosePrefectureBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        var teki = findViewById<TextView>(R.id.enemie)
        var hyo = findViewById<TextView>(R.id.hyougo_bt)
        var kyo = findViewById<TextView>(R.id.kyoto_bt)
        var si = findViewById<TextView>(R.id.siga_bt)
        var osa = findViewById<TextView>(R.id.osaka_bt)
        var na = findViewById<TextView>(R.id.nara_bt)
        var mi = findViewById<TextView>(R.id.mie_bt)

        val myForce: Int = intent.getIntExtra("MY_FORCE", 0)


        /* 進行先の地図を押す */
        /* 今回は近畿地方のみだが、全国に広げる場合は地方選択からこのActivityに移動する。 */
        /* enemieText変化 */
        osa.setOnClickListener { teki.setText(R.string.osaka) }
        kyo.setOnClickListener { teki.setText(R.string.kyoto) }
        na.setOnClickListener { teki.setText(R.string.nara) }
        hyo.setOnClickListener { teki.setText(R.string.hyogo) }
        mi.setOnClickListener { teki.setText(R.string.mie) }
        si.setOnClickListener { teki.setText(R.string.shiga) }
        /* enemieText変化 */

        binding.osaka.setOnClickListener { onPrefectureButtonTapped("osaka", myForce) }
        binding.kyoto.setOnClickListener { onPrefectureButtonTapped("kyoto", myForce) }
        binding.nara.setOnClickListener { onPrefectureButtonTapped("nara", myForce) }
        binding.hyogo.setOnClickListener { onPrefectureButtonTapped("hyogo", myForce) }
        binding.wakayama.setOnClickListener { onPrefectureButtonTapped("wakayama", myForce) }
        binding.mie.setOnClickListener { onPrefectureButtonTapped("mie", myForce) }
        binding.siga.setOnClickListener { onPrefectureButtonTapped("siga", myForce) }
//        binding.osakaBt.setOnClickListener { onPrefectureButtonTapped("osaka", myForce)
//        //enemylevel =
//            teki.setText(R.string.osaka)
//        }
//        binding.kyotoBt.setOnClickListener { onPrefectureButtonTapped("kyoto", myForce)
//        //enemylevel =
//        }
//        binding.naraBt.setOnClickListener { onPrefectureButtonTapped("nara", myForce)
//        //enemylevel =
//            teki.setText(R.string.nara)
//        }
//        binding.hyougoBt.setOnClickListener { onPrefectureButtonTapped("hyogo", myForce)
//        //enemylevel =
//            teki.setText(R.string.hyogo)
//        }
//        binding.wakayamaBt.setOnClickListener { onPrefectureButtonTapped("wakayama", myForce)
//        //enemylevel =
//        }
//        binding.mieBt.setOnClickListener { onPrefectureButtonTapped("mie", myForce)
//        //enemylevel =
//            teki.setText(R.string.mie)
//         }
//        binding.sigaBt.setOnClickListener { onPrefectureButtonTapped("siga", myForce)
//        //enemylevel =
//            teki.setText(R.string.shiga)
//        }

        /* 戻るボタン */
        binding.backbutton2.setOnClickListener{
            val intent = Intent( this, GameActivity::class.java )
            startActivity(intent)
        }
        binding.attackbutton2.setOnClickListener {
            val intent = Intent(this, LastCheckActivity::class.java)
            startActivity(intent)
        }
    }


    /* 最終確認画面へ */
    private fun onPrefectureButtonTapped(prefName: String, myForce: Int){

            val intent = Intent(this, LastCheckActivity::class.java)
            startActivity(intent)
            // LastCheckActivityに選択した都道府県名を渡す
            intent.putExtra("PREF_NAME", prefName)
            intent.putExtra("MY_FORCE", myForce)

            //相手の情報
            intent.putExtra("enemy", enemylevel.toInt())

            startActivity(intent)

    }
}