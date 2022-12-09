package com.example.sotsukenappproject

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.bluetooth.BluetoothA2dp
import android.content.Intent
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.UpdateAppearance
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import com.example.sotsukenappproject.databinding.ActivityLastCheckBinding

class LastCheckActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLastCheckBinding


    var currentAnimator: Animator? = null
    var shortAnimationDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)


        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)

        // プレイヤーの兵力を受け取る
//        val myForce: Int = intent.getIntExtra("MY_FORCE", 0)
//
//        // ChoosePrefectureActivityから選択した都道府県名を受け取る
//        val prefName: String = intent.getStringExtra("PREF_NAME")!!
//
//        // それぞれの国の兵力を持つマップを作成
//        val enemyStrength: Map<String, Int> = mapOf(
//            "osaka" to 100000,
//            "kyoto" to 100000,
//            "nara" to 100000,
//            "hyogo" to 100000,
//            "wakayama" to 100000,
//            "mie" to 100000,
//            "siga" to 100000
//        )

        // prefNameによって表示する情報を変更する

//        // 敵国の兵力
//        val enemyForce: Int = enemyStrength[prefName]!!
//        // 進行時間
//        val attackTime: Long = calcAttackTime(myForce, enemyForce).toLong()

        // 戻るボタン(進行先選択)
        binding.backButton.setOnClickListener{
            val intent = Intent(this, ChoosePrefectureActivity::class.java )
            startActivity(intent)
        }
        // 進行を開始する
        binding.startAttackButton.setOnClickListener{
            val intent = Intent(this, AttackCountDownActivity::class.java )
            startActivity(intent)
//            intent.putExtra("ATTACK_TIME", attackTime)
//            intent.putExtra("PREF_NAME", prefName)
        }

        // 自国と敵国の兵力と所要時間を表示する
//        showEnemyStatus(prefName, myForce, enemyForce, attackTime)
    }

//    /* 進行時間の計算 */
//    private fun calcAttackTime(myForce: Int,enemyForce: Int): Int {
//        val progressTime: Int // 進行時間(秒)
//        val basicTime: Int = 30 * 60 // 基礎進行時間(秒)
//        val earliestTime: Int = 10 * 60 // 最短進行時間(秒)
//        val differenceForce: Int = enemyForce - myForce // 敵国から見た兵力差
//
//        progressTime = if(differenceForce <= -100){
//            // 自国の兵力が100以上優勢のとき
//            earliestTime
//        } else if (differenceForce < 0) {
//            // 自国の兵力が優勢のとき
//            basicTime + (differenceForce / 5)
//        } else if (differenceForce > 0){
//            // 敵国の兵力が優勢のとき
//            basicTime + 3 * (differenceForce / 5)
//        } else {
//            // その他(兵力差がないとき)
//            basicTime
//        }
//        return progressTime
//    }
//
//    /* 情報を表示する(テキスト要素の書き換え) */
//    private fun showEnemyStatus(enemyName: String,myForce: Int,enemyForce: Int,attackTime: Long){
//        binding.enemyName.setText( "進行先：" + enemyName )
//        binding.myForce.setText( "自国の兵力：" + myForce.toString() )
//        binding.enemyForce.setText( "敵国の兵力" + enemyForce.toString() )
//
//        val hour: Long = attackTime / 1000L / 60L / 60L
//        val minute: Long = attackTime / 1000L / 60L
//        val second: Long = attackTime / 1000L % 60L
//        binding.attackTime.setText("所要時間：%1d時間%2$02d分%3$02d秒".format(hour, minute, second))
//    }

}