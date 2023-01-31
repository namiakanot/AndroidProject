package com.example.sotsukenappproject

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.sotsukenappproject.databinding.FragmentCheckpop2Binding

class checkpopFragment2 : DialogFragment(){

    private var _binding: FragmentCheckpop2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentCheckpop2Binding.inflate(inflater, container, false)
        super.onCreate(savedInstanceState)

        binding.yesbutton.setOnClickListener{
            startActivity(Intent(context, GameActivity::class.java))
        }

        binding.nobutton.setOnClickListener{
            _binding = null

        }
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}