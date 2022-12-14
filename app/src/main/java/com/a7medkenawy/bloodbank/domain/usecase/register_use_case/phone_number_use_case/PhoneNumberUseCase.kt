package com.a7medkenawy.bloodbank.domain.usecase.register_use_case.phone_number_use_case

import android.app.Activity
import android.widget.Toast
import com.a7medkenawy.bloodbank.domain.repository.FirebaseRepository
import com.a7medkenawy.bloodbank.presentation.intro.regestration.sgininwithphonenumber.VerifyViewModel
import javax.inject.Inject

class PhoneNumberUseCase @Inject constructor(
    val firebaseRepository: FirebaseRepository,
) {
    suspend fun signInWithPhoneNumber(
        phoneNumber: String,
        activity: Activity,
        viewModel: VerifyViewModel,
    ) {
        firebaseRepository.signInWithPhoneNumber(phoneNumber, activity, viewModel)

    }


}