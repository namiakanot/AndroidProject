@file:Suppress("DEPRECATION")

package com.example.sotsukenappproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.sotsukenappproject.databinding.ActivityChoosePrefectureBinding
import androidx.preference.PreferenceManager

class ChoosePrefectureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChoosePrefectureBinding

    var enemylevel : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChoosePrefectureBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        val userForce = pref.getInt("USER_FORCE",960)
        val nextPref = pref.getInt("WON_COUNT",0)
        val prefForce: Array<Int> = arrayOf(1360,1460,1780,2610,5530,8830)
        val prefName: Array<String> = arrayOf("R.string.nara","R.string.mie","R.string.siga","R.string.kyoto","R.string.hyougo","R.string.osaka")
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragment = PopFragment()

        /* enemieText変化 */
        val enemyName = prefName[nextPref]
        val enemyForce = prefForce[nextPref]

        binding.enemie.text = enemyName

//        binding.osakaBt.setOnClickListener {
//            binding.enemie.text = R.string.osaka.toString()
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.add(R.id.fragment_container_view_tag, fragment)
//            fragmentTransaction.commit()
//        }


//        osa.setOnClickListener { teki.setText(R.string.osaka)
//            val dialog = PopFragment()
//            fragmentManager.run{
//                dialog.show(this,"osaka")
//            }
//            val args = Bundle()
//            args.putString("enemy_name","osaka")
//            args.putInt("enemy_force", enemyForce["osaka"]!!)
//            args.putInt("user_force", userForce)
//            val fragment = PopFragment()
//            binding.popFragmentView.visibility = View.VISIBLE
//
//            fragment.arguments = args
//        }

        /* enemieText変化 */

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
}