package com.example.sotsukenappproject

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.sotsukenappproject.databinding.FragmentCheckpopBinding

class CheckPopFragment : DialogFragment(){

    private var _binding: FragmentCheckpopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentCheckpopBinding.inflate(inflater, container, false)
        super.onCreate(savedInstanceState)

        binding.yesbutton.setOnClickListener{
            val intent = Intent(context, CampActivity::class.java)
            val campFailedFlag = true
            intent.putExtra("CAMP_FAILED_FLAG",campFailedFlag)
            startActivity(intent)
        }

        binding.nobutton.setOnClickListener{
            _binding = null

            onDestroy()
        }
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
