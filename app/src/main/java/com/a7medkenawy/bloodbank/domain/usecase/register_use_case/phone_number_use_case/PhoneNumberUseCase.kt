package com.a7medkenawy.bloodbank.domain.usecase.register_use_case.phone_number_use_case

import android.app.Activity
import android.widget.Toast
import com.a7medkenawy.bloodbank.domain.repository.FirebaseRepository
import com.a7medkenawy.bloodbank.presentation.intro.regestration.virefy.VerifyViewModel
import javax.inject.Inject

class PhoneNumberUseCase @Inject constructor(
    val firebaseRepository: FirebaseRepository,
) {
    fun signInWithPhoneNumber(phoneNumber: String, activity: Activity, viewModel: VerifyViewModel) {
        if (phoneNumber.trim().isNotEmpty()) {
            firebaseRepository.signInWithPhoneNumber(phoneNumber, activity, viewModel)
        } else {
            Toast.makeText(activity.applicationContext, "Fields cannot be empty", Toast.LENGTH_LONG)
                .show()
        }
    }
}