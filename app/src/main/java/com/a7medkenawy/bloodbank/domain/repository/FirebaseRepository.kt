package com.a7medkenawy.bloodbank.domain.repository

import android.app.Activity
import com.a7medkenawy.bloodbank.presentation.intro.regestration.sgininwithphonenumber.VerifyViewModel

interface FirebaseRepository {

    suspend fun signInWithPhoneNumber(
        phoneNumber: String, activity: Activity,
        viewModel: VerifyViewModel,
    )

    suspend fun signInWithGoogle(activity: Activity,serverClient: String)

    suspend fun  signInWithFacebook(activity: Activity)

}