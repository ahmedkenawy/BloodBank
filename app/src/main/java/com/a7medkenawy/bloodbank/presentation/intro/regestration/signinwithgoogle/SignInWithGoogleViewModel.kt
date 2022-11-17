package com.a7medkenawy.bloodbank.presentation.intro.regestration.signinwithgoogle

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInWithGoogleViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {
    fun signInWithGoogle(activity: Activity,serverClient: String) = viewModelScope.launch(Dispatchers.IO) {
        registerUseCase.signInWithGoogleUseCase.signInWithGoogle(activity,serverClient)
    }
}