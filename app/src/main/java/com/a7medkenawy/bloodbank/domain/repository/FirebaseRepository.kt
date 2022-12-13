package com.a7medkenawy.bloodbank.domain.repository

import android.app.Activity
import android.net.Uri
import com.a7medkenawy.bloodbank.domain.model.user.User
import com.a7medkenawy.bloodbank.presentation.intro.complete_registration.CompleteRegistrationViewModel
import com.a7medkenawy.bloodbank.presentation.intro.regestration.sgininwithphonenumber.VerifyViewModel

interface FirebaseRepository {

    suspend fun signInWithPhoneNumber(
        phoneNumber: String, activity: Activity,
        viewModel: VerifyViewModel,
    )

    suspend fun signInWithGoogle(activity: Activity, serverClient: String)

    suspend fun signInWithFacebook(activity: Activity)

    suspend fun registerUser(
        user: User,
        completeRegistrationViewModel: CompleteRegistrationViewModel,
    )

    suspend fun uploadImageToCloudStorage(
        activity: Activity,
        imageUri: Uri,
        completeRegistrationViewModel: CompleteRegistrationViewModel,
    )

}