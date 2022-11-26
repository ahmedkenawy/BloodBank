package com.a7medkenawy.bloodbank.presentation.intro.complete_registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.a7medkenawy.bloodbank.R
import com.a7medkenawy.bloodbank.databinding.FragmentCompleteRestrationScreenBinding
import com.a7medkenawy.bloodbank.utils.Constants.value
import com.a7medkenawy.bloodbank.utils.TypesOfRegistration
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CompleteRegistrationScreen : Fragment() {

    lateinit var binding: FragmentCompleteRestrationScreenBinding

    @Inject
    lateinit var auth: FirebaseAuth
    private lateinit var completeRegistrationViewModel: CompleteRegistrationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCompleteRestrationScreenBinding.inflate(inflater, container, false)
        completeRegistrationViewModel =
            ViewModelProvider(requireActivity())[CompleteRegistrationViewModel::class.java]

        setupSpinnerAdapter()
        handleAllRegistrationState()

        return binding.root
    }

    private fun handleAllRegistrationState() {
        if (registerWithGoogle()) {
            Toast.makeText(requireActivity(), "google", Toast.LENGTH_LONG).show()
            binding.userIv.load(auth.currentUser?.photoUrl)
        } else if (registerWithOtp()) {
            Toast.makeText(requireActivity(), "otp", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireActivity(), "facebook", Toast.LENGTH_LONG).show()
        }
    }

    private fun registerWithOtp() =
        completeRegistrationViewModel.registrationType.value == TypesOfRegistration.OTP.toString()

    private fun registerWithGoogle() =
        completeRegistrationViewModel.registrationType.value == TypesOfRegistration.GOOGLE.toString()

    private fun setupSpinnerAdapter() {
        val arrayAdapter = ArrayAdapter(requireActivity(), R.layout.style_spinner, value)
        binding.spinner.adapter = arrayAdapter
    }


}