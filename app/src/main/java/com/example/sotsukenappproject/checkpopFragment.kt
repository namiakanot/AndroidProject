package com.example.sotsukenappproject

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.sotsukenappproject.databinding.FragmentCheckpopBinding

class checkpopFragment : DialogFragment(){

    private var _binding: FragmentCheckpopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentCheckpopBinding.inflate(inflater, container, false)
        super.onCreate(savedInstanceState)


        binding.yesbutton.setOnClickListener{
            startActivity(Intent(context, CampActivity::class.java))
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