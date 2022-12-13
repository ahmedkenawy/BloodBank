package com.a7medkenawy.bloodbank.presentation.intro.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.a7medkenawy.bloodbank.R
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreen : Fragment() {


    @Inject
    lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        Handler().postDelayed({
            if (mAuth.currentUser!=null){
                findNavController().navigate(R.id.action_registerationScreen_to_completeRegistrationScreen)
            }else if (onBoardingFinished()) {
                findNavController().navigate(R.id.action_splashScreen_to_registerationScreen2)
            } else {
                findNavController().navigate(R.id.action_splashScreen_to_fragmentViewPager)
            }
        }, 3000)

        return view
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("finished", false)
    }
}