package com.example.sotsukenappproject

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.example.sotsukenappproject.databinding.FragmentCheckpop2Binding

class checkpopFragment2 : DialogFragment(){

    private var _binding: FragmentCheckpop2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentCheckpop2Binding.inflate(inflater, container, false)
        super.onCreate(savedInstanceState)

        binding.yesbutton.setOnClickListener{
            startActivity(Intent(context, GameActivity::class.java))
            val intent = Intent(context, LastCheckActivity::class.java)
            intent.putExtra("LOSE_COUNT",1)
        }

        binding.nobutton.setOnClickListener{
            onDestroy()
        }
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}