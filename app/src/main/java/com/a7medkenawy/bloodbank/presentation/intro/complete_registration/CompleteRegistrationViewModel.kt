package com.a7medkenawy.bloodbank.presentation.intro.complete_registration

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CompleteRegistrationViewModel @Inject constructor() : ViewModel() {

    var phoneNumber = MutableStateFlow("null")
    var registrationType = MutableStateFlow("null")

    fun returnRegistrationType(regType: String) {
        registrationType.value=regType
    }

    fun sendPhoneNumberToCompleteRegistration(phoneNumber: String) {
        this.phoneNumber.value = phoneNumber
    }
}