package com.example.sotsukenappproject

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.sotsukenappproject.databinding.ActivityChoosePrefectureBinding

class ChoosePrefectureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChoosePrefectureBinding

    var enemylevel : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChoosePrefectureBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        // GameActivityから
        /**
         * nextAttackの値による挙動
         * (1)nextAttack < 7 のとき
         * kinki_enemy["nara","mie","siga","kyoto","hyogo","osaka"]
         * kinki_enemy[nextAttack] の国へ進行する
         */
        val nextAttack: Int = intent.getIntExtra("gameClearCount", 0)

        // val userName: String = intent.getStringExtra("USER_NAME")!!
        val userForce: Int = intent.getIntExtra("USER_FORCE", 960)
        val attackCount = intent.getIntExtra("KINKI_ATTACKED_COUNT", 0)

        val enemyForce: Map<String, Int> = mapOf(
            "siga" to 1780,
            "kyoto" to 2610,
            "osaka" to 8830,
            "hyogo" to 5530,
            "nara" to 1360,
        // 和歌山は1周目では進行済みである
            "wakayama" to 960,
            "mie" to 1410
        )
        // 取得するとき -> enemyForce["osaka"] -> 8830

        val teki = findViewById<TextView>(R.id.enemie)
        val hyo = findViewById<TextView>(R.id.hyougo_bt)
        val kyo = findViewById<TextView>(R.id.kyoto_bt)
        val si = findViewById<TextView>(R.id.siga_bt)
        val osa = findViewById<TextView>(R.id.osaka_bt)
        val na = findViewById<TextView>(R.id.nara_bt)
        val mi = findViewById<TextView>(R.id.mie_bt)

        val fragmentManager: FragmentManager = supportFragmentManager

//        binding.playerName.text = userName
//        binding.wakayama.setBackgroundResource(R.color.blue_overlay)
//        binding.nara.setBackgroundResource(R.color.red)
//        changePrefColor(nextAttack)
        // binding.playerName.text = userName
//        binding.wakayama.setBackgroundResource(R.color.blue_overlay)
//        binding.nara.setBackgroundResource(R.color.red)
//        changePrefColor(nextAttack)
        /* 進行先の地図を押す */
        /* 今回は近畿地方のみだが、全国に広げる場合は地方選択からこのActivityに移動する。 */

        /* enemieText変化 */
        osa.setOnClickListener { teki.setText(R.string.osaka)
            val dialog = popFragment()
            fragmentManager.run{
                dialog.show(this,"osaka")
            }
            val args = Bundle()
            args.putString("enemy_name","osaka")
            args.putInt("enemy_force", enemyForce["osaka"]!!)
            args.putInt("user_force", userForce)
            val fragment = popFragment()

            fragment.arguments = args
        }
        kyo.setOnClickListener { teki.setText(R.string.kyoto)
            val dialog = popFragment()
            fragmentManager.run{
                dialog.show(this,"kyoto")
            }
            val args = Bundle()
            args.putString("enemy_name","kyoto")
            args.putInt("enemy_force", enemyForce["kyoto"]!!)
            args.putInt("user_force", userForce)
            val fragment = popFragment()

            fragment.arguments = args
        }
        na.setOnClickListener { teki.setText(R.string.nara)
            val dialog = popFragment()
            fragmentManager.run{
                dialog.show(this,"nara")
            }
            val args = Bundle()
            args.putString("enemy_name","nara")
            args.putInt("enemy_force", enemyForce["nara"]!!)
            args.putInt("user_force", userForce)
            val fragment = popFragment()

            fragment.arguments = args
        }
        hyo.setOnClickListener { teki.setText(R.string.hyogo)
            val dialog = popFragment()
            fragmentManager.run{
                dialog.show(this,"hyogo")
            }
            val args = Bundle()
            args.putString("enemy_name","hyogo")
            args.putInt("enemy_force", enemyForce["hyogo"]!!)
            args.putInt("user_force", userForce)
            val fragment = popFragment()

            fragment.arguments = args
        }
        mi.setOnClickListener { teki.setText(R.string.mie)
            val dialog = popFragment()
            fragmentManager.run{
                dialog.show(this,"mie")
            }
            val args = Bundle()
            args.putString("enemy_name","mie")
            args.putInt("enemy_force", enemyForce["mie"]!!)
            args.putInt("user_force", userForce)
            val fragment = popFragment()

            fragment.arguments = args
        }
        si.setOnClickListener { teki.setText(R.string.siga)
            val dialog = popFragment()
            fragmentManager.run{
                dialog.show(this,"siga")
            }
            val args = Bundle()
            args.putString("enemy_name","siga")
            args.putInt("enemy_force", enemyForce["siga"]!!)
            args.putInt("user_force", userForce)
            val fragment = popFragment()

            fragment.arguments = args
        }
        /* enemieText変化 */

//        この機能はβ版では実装しない
//
//        binding.osaka.setOnClickListener { onPrefectureButtonTapped("osaka", myForce) }
//        binding.kyoto.setOnClickListener { onPrefectureButtonTapped("kyoto", myForce) }
//        binding.nara.setOnClickListener { onPrefectureButtonTapped("nara", myForce) }
//        binding.hyogo.setOnClickListener { onPrefectureButtonTapped("hyogo", myForce) }
//        binding.wakayama.setOnClickListener { onPrefectureButtonTapped("wakayama", myForce) }
//        binding.mie.setOnClickListener { onPrefectureButtonTapped("mie", myForce) }
//        binding.siga.setOnClickListener { onPrefectureButtonTapped("siga", myForce) }

        /* 戻るボタン */
        binding.backbutton2.setOnClickListener{
            val intent = Intent( this, GameActivity::class.java )
            startActivity(intent)
        }
        binding.attackbutton2.setOnClickListener {
            val intent = Intent(this, LastCheckActivity::class.java)
            intent.putExtra("KINKI_ATTACKED_COUNT",attackCount)
            intent.putExtra("NEXT_ATTACK",nextAttack)
            startActivity(intent)
        }
    }

//    この機能はβ版では実装しない
//    /* 最終確認画面へ */
//    private fun onPrefectureButtonTapped(prefName: String, myForce: Int){
//
//            val intent = Intent(this, LastCheckActivity::class.java)
//            startActivity(intent)
//            // LastCheckActivityに選択した都道府県名を渡す
//            intent.putExtra("PREF_NAME", prefName)
//            intent.putExtra("MY_FORCE", myForce)
//
//            //相手の情報
//            intent.putExtra("enemy", enemylevel.toInt())
//
//            startActivity(intent)
//
//    }

    private fun changePrefColor(nextAttack: Int){
        val kinkiEnemy: Array<String> = arrayOf("nara", "mie", "siga", "kyoto", "hyogo", "osaka")
        val changeBlue = kinkiEnemy[nextAttack]

        // 進行済み -> 青, 次回進行可能 -> 赤
        when (changeBlue) {
            "nara" -> {
                binding.nara.setBackgroundResource(R.color.blue_overlay)
                binding.mie.setBackgroundResource(R.color.red)
            }
            "mie" -> {
                binding.mie.setBackgroundResource(R.color.blue_overlay)
                binding.siga.setBackgroundResource(R.color.red)
            }
            "siga" -> {
                binding.siga.setBackgroundResource(R.color.blue_overlay)
                binding.kyoto.setBackgroundResource(R.color.red)
            }
            "kyoto" -> {
                binding.kyoto.setBackgroundResource(R.color.blue_overlay)
                binding.hyogo.setBackgroundResource(R.color.red)
            }
            "hyogo" -> {
                binding.hyogo.setBackgroundResource(R.color.blue_overlay)
                binding.osaka.setBackgroundResource(R.color.red)
            }
            else -> binding.osaka.setBackgroundResource(R.color.blue_overlay)
        }
    }
}