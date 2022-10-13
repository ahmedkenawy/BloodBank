package com.a7medkenawy.bloodbank.data.repository

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.a7medkenawy.bloodbank.domain.repository.FirebaseRepository
import com.a7medkenawy.bloodbank.presentation.intro.regestration.virefy.VerifyViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : FirebaseRepository {

    override fun signInWithPhoneNumber(
        phoneNumber: String,
        activity: Activity,
        viewModel: VerifyViewModel,
    ) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(callbacks(viewModel))          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)


    }

    private fun callbacks(viewModel: VerifyViewModel): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(e: FirebaseException) {

                if (e is FirebaseAuthInvalidCredentialsException) {
                    Log.d("KENO", "onVerificationFailed: "+e.message)
                } else if (e is FirebaseTooManyRequestsException) {
                    Log.d("KENO", "onVerificationFailed: "+e.message)
                }

                // Show a message and update the UI
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken,
            ) {
                viewModel.sendVerificationCode(verificationId)
            }
        }
        return callbacks
    }
}