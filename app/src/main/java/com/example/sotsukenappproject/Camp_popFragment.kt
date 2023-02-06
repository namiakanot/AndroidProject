package com.example.sotsukenappproject

import android.content.Intent
import android.media.SoundPool
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.sotsukenappproject.databinding.FragmentCampPopBinding


class Camp_popFragment : DialogFragment(){

    private var _binding: FragmentCampPopBinding? = null
    private val binding get() = _binding!!
    private lateinit var soundPool: SoundPool
    private var soundResId = 0
    private var soundResId2 = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentCampPopBinding.inflate(inflater, container, false)


        binding.backbutton.setOnClickListener{
            val intent = Intent(context, GameActivity::class.java)
            startActivity(intent)
            soundPool.play(soundResId2, 1.0f, 100f, 0, 0, 1.0f)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}