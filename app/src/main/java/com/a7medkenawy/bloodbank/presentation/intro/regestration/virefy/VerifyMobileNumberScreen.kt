package com.a7medkenawy.bloodbank.presentation.intro.regestration.virefy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.a7medkenawy.bloodbank.R
import com.a7medkenawy.bloodbank.databinding.FragmentVerifyMobileNumberScreenBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyMobileNumberScreen : Fragment() {

    private lateinit var binding: FragmentVerifyMobileNumberScreenBinding
    lateinit var viewModel: VerifyViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentVerifyMobileNumberScreenBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[VerifyViewModel::class.java]
        binding.verifyScreenBackBtn.setOnClickListener {
            findNavController()
                .navigate(R.id.action_verifyMobileNumberScreen_to_registerationScreen)
        }
        binding.getOtpBtn.setOnClickListener {
            if (binding.phoneNumberInput.text.toString().trim().isEmpty()) {
                Snackbar.make(binding.verifyLayout, "Phone Number is Empty", Snackbar.LENGTH_LONG)
                    .setAction("Dismiss") {}
                    .show()
            } else {
                viewModel.signInWithPhoneNumber("+20${binding.phoneNumberInput.text}",
                    requireActivity(), viewModel)
                val action =
                    VerifyMobileNumberScreenDirections.actionVerifyMobileNumberScreenToSendOtpScreen(
                        "+20" + binding.phoneNumberInput.text.toString())
                findNavController().navigate(action)
            }
        }

        return binding.root
    }
}