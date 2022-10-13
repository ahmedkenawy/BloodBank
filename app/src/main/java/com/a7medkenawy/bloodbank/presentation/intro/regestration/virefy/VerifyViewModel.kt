package com.a7medkenawy.bloodbank.presentation.intro.regestration.virefy

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    ViewModel() {
    var verificationId = MutableStateFlow("nothing")
    var isRegisterSuccessfully = MutableStateFlow(false)
    fun signInWithPhoneNumber(phoneNumber: String, activity: Activity, viewModel: VerifyViewModel) {
        registerUseCase.phoneNumberUseCase.signInWithPhoneNumber(phoneNumber, activity, viewModel)
    }

    fun sendVerificationCode(verificationId: String) {
        this.verificationId.value = verificationId
    }

    fun isRegisterSuccessfully(case: Boolean) {
        isRegisterSuccessfully.value = case
    }
}