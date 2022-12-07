package com.example.sotsukenappproject

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.example.sotsukenappproject.databinding.FragmentNameBinding

class NameFragment : DialogFragment(){

    private var _binding: FragmentNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentNameBinding.inflate(inflater, container, false)
        super.onCreate(savedInstanceState)


        binding.finishbutton.setOnClickListener{
            // エディットテキストのテキストを取得
            val stringText = binding.editTextTextPersonName.text.toString()

            binding.finishbutton.text = stringText
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
