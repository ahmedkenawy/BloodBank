package com.a7medkenawy.bloodbank.presentation.intro.regestration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.a7medkenawy.bloodbank.R
import com.a7medkenawy.bloodbank.databinding.FragmentRegisterationScreenBinding
import com.a7medkenawy.bloodbank.presentation.intro.complete_registration.CompleteRegistrationViewModel
import com.a7medkenawy.bloodbank.presentation.intro.regestration.signinwithfacebook.SignInWithFacebookViewModel
import com.a7medkenawy.bloodbank.presentation.intro.regestration.signinwithgoogle.SignInWithGoogleViewModel
import com.a7medkenawy.bloodbank.utils.TypesOfRegistration
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint

class RegistrationScreen : Fragment() {
    @Inject
    lateinit var mAuth: FirebaseAuth
    private lateinit var signInWithGoogleViewModel: SignInWithGoogleViewModel
    private lateinit var signInWithFacebookViewModel: SignInWithFacebookViewModel
    private lateinit var completeRegistrationViewModel: CompleteRegistrationViewModel
    private lateinit var binding: FragmentRegisterationScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterationScreenBinding.inflate(inflater, container, false)
        signInWithGoogleViewModel =
            ViewModelProvider(requireActivity())[SignInWithGoogleViewModel::class.java]
        signInWithFacebookViewModel =
            ViewModelProvider(requireActivity())[SignInWithFacebookViewModel::class.java]
        completeRegistrationViewModel =
            ViewModelProvider(requireActivity())[CompleteRegistrationViewModel::class.java]

        registerWithOtp()
        registerWithGoogle()
        registerWithFacebook()
        return binding.root
    }

    private fun registerWithOtp() {
        binding.phoneBtn.setOnClickListener {
            findNavController().navigate(R.id.action_registerationScreen_to_verifyMobileNumberScreen)
            val otp:String=TypesOfRegistration.OTP.toString()
            completeRegistrationViewModel.returnRegistrationType(otp)
        }
    }

    private fun registerWithFacebook() {
        binding.fbBtn.setOnClickListener {
            signInWithFacebookViewModel.signInWithFacebook(requireActivity())
        }
    }

    private fun registerWithGoogle() {
        binding.googleBtn.setOnClickListener {
            signInWithGoogleViewModel.signInWithGoogle(requireActivity(),
                getString(R.string.default_web_client_id))
            val google:String=TypesOfRegistration.GOOGLE.toString()
            completeRegistrationViewModel.returnRegistrationType(google)
        }
    }


}