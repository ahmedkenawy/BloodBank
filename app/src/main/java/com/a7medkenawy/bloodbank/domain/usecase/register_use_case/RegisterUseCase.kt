package com.a7medkenawy.bloodbank.domain.usecase.register_use_case

import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.phone_number_use_case.PhoneNumberUseCase

data class RegisterUseCase(
    val phoneNumberUseCase: PhoneNumberUseCase
) {
}