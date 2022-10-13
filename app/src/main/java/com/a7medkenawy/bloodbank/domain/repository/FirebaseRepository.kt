package com.a7medkenawy.bloodbank.domain.repository

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.a7medkenawy.bloodbank.presentation.intro.regestration.virefy.VerifyViewModel

interface FirebaseRepository {

    fun signInWithPhoneNumber(
        phoneNumber: String, activity: Activity,
        viewModel: VerifyViewModel,
    )
}