package com.a7medkenawy.bloodbank.domain.usecase.register_use_case.sign_in_with_facebook_use_case

import android.app.Activity
import com.a7medkenawy.bloodbank.domain.repository.FirebaseRepository
import javax.inject.Inject

class SignInWithFacebookUseCase @Inject constructor(
    val firebaseRepository: FirebaseRepository,
) {
    suspend fun signInWithFacebook(activity: Activity) {
        firebaseRepository.signInWithFacebook(activity)
    }
}