package com.a7medkenawy.bloodbank.presentation.intro.regestration.sgininwithphonenumber

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    ViewModel() {
    var verificationId = MutableStateFlow("nothing")
    var isRegisterSuccessfully = MutableStateFlow(false)
    fun signInWithPhoneNumber(phoneNumber: String, activity: Activity, viewModel: VerifyViewModel) =
        viewModelScope.launch(Dispatchers.IO) {
            registerUseCase.phoneNumberUseCase.signInWithPhoneNumber(phoneNumber,
                activity,
                viewModel)
        }

    fun sendVerificationCode(verificationId: String) {
        this.verificationId.value = verificationId
    }

    fun isRegisterSuccessfully(case: Boolean) {
        isRegisterSuccessfully.value = case
    }
}