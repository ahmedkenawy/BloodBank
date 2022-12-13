package com.a7medkenawy.bloodbank.presentation.intro.complete_registration

import android.app.Activity
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a7medkenawy.bloodbank.domain.model.user.User
import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.complete_reg_usecase.CompleteRegistrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompleteRegistrationViewModel @Inject constructor(
    private val completeRegistrationUseCase: CompleteRegistrationUseCase,
) : ViewModel() {

    var phoneNumber = MutableStateFlow("null")
    var registrationType = MutableStateFlow("null")
    var imageUrl = MutableStateFlow("null")
    var isRegister = MutableStateFlow(false)

    fun returnRegistrationType(regType: String) {
        registrationType.value = regType
    }

    fun sendPhoneNumberToCompleteRegistration(phoneNumber: String) {
        this.phoneNumber.value = phoneNumber
    }

    fun registerUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        completeRegistrationUseCase.registerUser(user, this@CompleteRegistrationViewModel)
    }

    fun setImageUrl(imageUrl: String) {
        this.imageUrl.value = imageUrl
    }

    fun setIsRegister(isRegistered: Boolean) {
        this.isRegister.value = isRegistered
    }

    fun uploadImageToCloudStorage(
        activity: Activity,
        imageUri: Uri,
    ) = viewModelScope.launch(Dispatchers.Main) {
        completeRegistrationUseCase.uploadImageToCloudStorage(activity,
            imageUri,
            this@CompleteRegistrationViewModel)
    }
}