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

        //ani
                binding.button1.setOnClickListener({
                    zoomImageFromThumb(binding.button1,R.drawable.pop)
                })

        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)

        // プレイヤーの兵力を受け取る
        val myForce: Int = intent.getIntExtra("MY_FORCE", 0)

        // ChoosePrefectureActivityから選択した都道府県名を受け取る
        val prefName: String = intent.getStringExtra("PREF_NAME")!!

        // それぞれの国の兵力を持つマップを作成
        val enemyStrength: Map<String, Int> = mapOf(
            "osaka" to 100000,
            "kyoto" to 100000,
            "nara" to 100000,
            "hyogo" to 100000,
            "wakayama" to 100000,
            "mie" to 100000,
            "siga" to 100000
        )

        // prefNameによって表示する情報を変更する

        // 敵国の兵力
        val enemyForce: Int = enemyStrength[prefName]!!
        // 進行時間
        val attackTime: Long = calcAttackTime(myForce, enemyForce).toLong()

        // 戻るボタン(進行先選択)
        binding.backButton.setOnClickListener{
            val intent = Intent(this, ChoosePrefectureActivity::class.java )
            startActivity(intent)
        }
        // 進行を開始する
        binding.startAttackButton.setOnClickListener{
            val intent = Intent(this, AttackCountDownActivity::class.java )
            startActivity(intent)
            intent.putExtra("ATTACK_TIME", attackTime)
            intent.putExtra("PREF_NAME", prefName)
        }

        // 自国と敵国の兵力と所要時間を表示する
        showEnemyStatus(prefName, myForce, enemyForce, attackTime)
    }
    private fun zoomImageFromThumb(thumbView: View, imageResId: Int) {
        currentAnimator?.cancel()

        val expandedImageView: ImageView = findViewById(R.id.pop)
        expandedImageView.setImageResource(imageResId)

        val startBoundsInt = Rect()
        val finalBoundsInt = Rect()
        val globalOffset = Point()

        thumbView.getGlobalVisibleRect(startBoundsInt)
        findViewById<View>(R.id.container)
            .getGlobalVisibleRect(finalBoundsInt, globalOffset)
        startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
        finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

        val startBounds = RectF(startBoundsInt)
        val finalBounds = RectF(finalBoundsInt)

        val startScale: Float
        if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {

            startScale = startBounds.height() / finalBounds.height()
            val startWidth: Float = startScale * finalBounds.width()
            val deltaWidth: Float = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {

            startScale = startBounds.width() / finalBounds.width()
            val startHeight: Float = startScale * finalBounds.height()
            val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        thumbView.alpha = 0f
        expandedImageView.visibility = View.VISIBLE

        expandedImageView.pivotX = 0f
        expandedImageView.pivotY = 0f

        currentAnimator = AnimatorSet().apply {
            play(
                ObjectAnimator.ofFloat(
                    expandedImageView,
                    View.X,
                    startBounds.left,
                    finalBounds.left)
            ).apply {
                with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top, finalBounds.top))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f))
            }
            duration = shortAnimationDuration.toLong()
            interpolator = DecelerateInterpolator()
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    currentAnimator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    currentAnimator = null
                }
            })
            start()
        }

        expandedImageView.setOnClickListener {
            currentAnimator?.cancel()

            currentAnimator = AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale))
                }
                duration = shortAnimationDuration.toLong()
                interpolator = DecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }
                })
                start()
            }
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
        return progressTime
    }

    /* 情報を表示する(テキスト要素の書き換え) */
    private fun showEnemyStatus(enemyName: String,myForce: Int,enemyForce: Int,attackTime: Long){
        binding.enemyName.setText( "進行先：" + enemyName )
        binding.myForce.setText( "自国の兵力：" + myForce.toString() )
        binding.enemyForce.setText( "敵国の兵力" + enemyForce.toString() )

        val hour: Long = attackTime / 1000L / 60L / 60L
        val minute: Long = attackTime / 1000L / 60L
        val second: Long = attackTime / 1000L % 60L
        binding.attackTime.setText("所要時間：%1d時間%2$02d分%3$02d秒".format(hour, minute, second))
    }

}