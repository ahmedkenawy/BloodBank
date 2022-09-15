package com.a7medkenawy.bloodbank.presentation.intro.regestration.virefy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.a7medkenawy.bloodbank.R
import com.a7medkenawy.bloodbank.databinding.FragmentVerifyMobileNumberScreenBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_third_screen.view.*

class VerifyMobileNumberScreen : Fragment() {

    private lateinit var binding: FragmentVerifyMobileNumberScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentVerifyMobileNumberScreenBinding.inflate(inflater, container, false)

        binding.getOtpBtn.setOnClickListener {
            if (binding.phoneNumberInput.text.toString().trim().isEmpty()) {
                Snackbar.make(binding.verifyLayout, "Phone Number is Empty", Snackbar.LENGTH_LONG)
                    .setAction("Dismiss", { view ->
                    })
                    .show()
            } else {
                findNavController().navigate(R.id.action_verifyMobileNumberScreen_to_sendOtpScreen)
            }
        }

        return binding.root
    }
}