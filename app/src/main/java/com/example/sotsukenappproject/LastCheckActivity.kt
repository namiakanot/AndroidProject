package com.example.sotsukenappproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sotsukenappproject.databinding.ActivityLastCheckBinding
import androidx.preference.PreferenceManager

class LastCheckActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLastCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLastCheckBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)


        val userForce = pref.getInt("USER_FORCE",960)
        val prefForce: Array<Int> = arrayOf(1360,1460,1780,2610,5530,8830)
        val attackPref = pref.getInt("WON_COUNT",0)
        val lostCount = pref.getInt("LOST_COUNT",0)
        val losingCount = pref.getInt("LOSING_COUNT",0)
        val loseCount = intent.getIntExtra("LOSE_COUNT",0)
        val enemyForce = prefForce[attackPref]
        pref.edit().putInt("LOST_COUNT",lostCount + loseCount)
            .putInt("LOSING_COUNT",losingCount + loseCount)
            .apply()

        var i = 0
        var j = 0
        while (i < prefForce.size){
            var factEnemyForce = prefForce[i]
            if (i != attackPref){
                if(lostCount - losingCount < 0){
                    while (j < losingCount - lostCount){
                        factEnemyForce *= 0.9.toInt()
                        j++
                    }
                }else if(lostCount - losingCount > 0){
                    while (j < lostCount - losingCount){
                        factEnemyForce *= 1.1.toInt()
                        j++
                    }
                }
                j = 0
            }
            prefForce[i] = factEnemyForce
            i++
        }

        // 進行時間
        val attackTime: Long = calcAttackTime(userForce, enemyForce).toLong()

        // 自国と敵国の兵力と所要時間を表示する
        showEnemyStatus(attackPref, userForce, enemyForce, attackTime)

        // 戻るボタン(進行先選択)
        binding.backButton.setOnClickListener{
            val intent = Intent(this, ChoosePrefectureActivity::class.java )
            startActivity(intent)
        }
        // 進行を開始する
        binding.startAttackButton.setOnClickListener{
            val intent = Intent(this, AttackCountDownActivity2::class.java)
            intent.putExtra("ATTACK_TIME",attackTime)
            pref.edit().putLong("ATTACK_TIME",attackTime)
                .apply()
            startActivity(intent)
        }
    }

    /* 進行時間の計算 */
    private fun calcAttackTime(myForce: Int,enemyForce: Int): Int {
        val progressTime: Int // 進行時間(秒)
        val basicTime: Int = 30 * 60 // 基礎進行時間(秒)
        val earliestTime: Int = 10 * 60 // 最短進行時間(秒)
        val differenceForce: Int = enemyForce - myForce // 敵国から見た兵力差

        progressTime = if(differenceForce <= -100){
            // 自国の兵力が100以上優勢のとき
            earliestTime
        } else if (differenceForce < 0) {
            // 自国の兵力が優勢のとき
            basicTime + (differenceForce / 5)
        } else if (differenceForce > 0){
            // 敵国の兵力が優勢のとき
            basicTime + 3 * (differenceForce / 5)
        } else {
            // その他(兵力差がないとき)
            basicTime
        }
        return progressTime * 1000
    }

    /* 情報を表示する(テキスト要素の書き換え) */
    @SuppressLint("SetTextI18n")
    private fun showEnemyStatus(enemyNumber: Int, myForce: Int, enemyForce: Int, attackTime: Long){
        val prefName: Array<String> = arrayOf("奈良","三重","滋賀","京都","兵庫","大阪")
        val enemyName = prefName[enemyNumber]
        binding.myarmy.text = "Player"
        binding.enemyName.text = enemyName
        binding.myForce.text = myForce.toString()
        binding.enemyForce.text = enemyForce.toString()

        val hour: Long = attackTime / 1000L / 60L / 60L
        val minute: Long = attackTime / 1000L / 60L
        val second: Long = attackTime / 1000L % 60L
        binding.attackTime.text = "%1d:%2$02d:%3$02d".format(hour, minute, second)
    }

}