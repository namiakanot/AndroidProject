package com.example.sotsukenappproject

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.sotsukenappproject.databinding.FragmentAFBinding
import com.example.sotsukenappproject.databinding.FragmentCampPopBinding

class Camp_popFragment : DialogFragment(){

    private var _binding: FragmentCampPopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentCampPopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}