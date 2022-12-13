package com.a7medkenawy.bloodbank.domain.usecase.register_use_case.complete_reg_usecase

import android.app.Activity
import android.net.Uri
import com.a7medkenawy.bloodbank.domain.model.user.User
import com.a7medkenawy.bloodbank.domain.repository.FirebaseRepository
import com.a7medkenawy.bloodbank.presentation.intro.complete_registration.CompleteRegistrationViewModel
import javax.inject.Inject


class CompleteRegistrationUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {
    suspend fun registerUser(
        user: User,
        completeRegistrationViewModel: CompleteRegistrationViewModel,
    ) {
        firebaseRepository.registerUser(user, completeRegistrationViewModel)
    }

    suspend fun uploadImageToCloudStorage(
        activity: Activity,
        imageUri: Uri,
        completeRegistrationViewModel: CompleteRegistrationViewModel,
    ) {
        firebaseRepository.uploadImageToCloudStorage(activity,
            imageUri,
            completeRegistrationViewModel)
    }
}