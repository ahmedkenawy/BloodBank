package com.a7medkenawy.bloodbank.presentation.intro.regestration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.a7medkenawy.bloodbank.R
import com.a7medkenawy.bloodbank.databinding.FragmentRegisterationScreenBinding
import com.a7medkenawy.bloodbank.databinding.FragmentSecondScreenBinding

class RegistrationScreen : Fragment() {

    private lateinit var binding:FragmentRegisterationScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentRegisterationScreenBinding.inflate(inflater,container,false)
        binding.phoneBtn.setOnClickListener {
            findNavController().navigate(R.id.action_registerationScreen_to_verifyMobileNumberScreen)
        }
        return binding.root
    }

}