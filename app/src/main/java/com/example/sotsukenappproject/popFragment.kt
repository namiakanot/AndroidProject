package com.example.sotsukenappproject

import android.os.Bundle
import android.service.voice.VoiceInteractionSession.VisibleActivityCallback
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.sotsukenappproject.databinding.FragmentPopBinding

class PopFragment : DialogFragment(){

    private var _binding: FragmentPopBinding? = null
    private val binding get() = _binding!!
    private val enemyName = requireArguments().getString("enemy_name")
    private val enemyForce = requireArguments().getInt("enemy_force")
    private val userForce = requireArguments().getInt("user_force")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentPopBinding.inflate(inflater, container, false)
        when(enemyName){
            "osaka" -> binding.popname.text = R.string.osaka.toString()
            "hyogo" -> binding.popname.text = R.string.hyogo.toString()
            "kyoto" -> binding.popname.text = R.string.kyoto.toString()
            "siga" -> binding.popname.text = R.string.siga.toString()
            "nara" -> binding.popname.text = R.string.nara.toString()
            "mie" -> binding.popname.text = R.string.mie.toString()
            "wakayama" -> binding.popname.text = R.string.wakayama.toString()
        }
        binding.textView16.text = enemyForce.toString()
        val attackTime = calcAttackTime(enemyForce, userForce)
        val hour = attackTime / 60
        val mimute = attackTime % 60
        binding.textView15.text = "%1d:%2$02d".format(hour,mimute)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun calcAttackTime(enemyForce: Int,userForce: Int): Int{
        val enemy = enemyForce
        val user = userForce
        val basicTime = 30
        val shortestTime = 10
        val differentForce = enemy - user

        if(differentForce > 0){
            return basicTime + differentForce / 5
        }else if(differentForce < -33){
            return shortestTime
        }else if(differentForce < 0){
            return basicTime + differentForce / 5 * 3
        }else{
            return basicTime
        }
    }
}