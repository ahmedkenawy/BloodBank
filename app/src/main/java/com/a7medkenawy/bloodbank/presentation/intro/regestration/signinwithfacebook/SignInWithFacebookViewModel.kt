package com.a7medkenawy.bloodbank.presentation.intro.regestration.signinwithfacebook

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignInWithFacebookViewModel @Inject constructor(val registerUseCase: RegisterUseCase) :
    ViewModel() {
        fun signInWithFacebook(activity: Activity)=viewModelScope.launch(Dispatchers.IO) {
            registerUseCase.signInWithFacebookUseCase.signInWithFacebook(activity)
        }
}