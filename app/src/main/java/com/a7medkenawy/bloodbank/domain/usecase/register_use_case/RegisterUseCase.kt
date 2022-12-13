package com.a7medkenawy.bloodbank.domain.usecase.register_use_case

import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.complete_reg_usecase.CompleteRegistrationUseCase
import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.phone_number_use_case.PhoneNumberUseCase
import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.sign_in_with_facebook_use_case.SignInWithFacebookUseCase
import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.sign_in_with_google_use_case.SignInWithGoogleUseCase

data class RegisterUseCase(
    val phoneNumberUseCase: PhoneNumberUseCase,
    val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    val signInWithFacebookUseCase: SignInWithFacebookUseCase,
    val completeRegistrationUseCase: CompleteRegistrationUseCase,
) {
}