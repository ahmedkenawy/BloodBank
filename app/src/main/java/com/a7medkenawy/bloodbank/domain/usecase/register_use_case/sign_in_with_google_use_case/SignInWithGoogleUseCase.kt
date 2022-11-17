package com.a7medkenawy.bloodbank.domain.usecase.register_use_case.sign_in_with_google_use_case

import android.app.Activity
import com.a7medkenawy.bloodbank.domain.repository.FirebaseRepository
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    val firebaseRepository: FirebaseRepository
) {
    suspend fun signInWithGoogle(activity: Activity,serverClient: String) {
        firebaseRepository.signInWithGoogle(activity,serverClient)
    }
}