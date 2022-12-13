package com.a7medkenawy.bloodbank.di

import com.a7medkenawy.bloodbank.domain.repository.FirebaseRepository
import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.RegisterUseCase
import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.complete_reg_usecase.CompleteRegistrationUseCase
import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.phone_number_use_case.PhoneNumberUseCase
import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.sign_in_with_facebook_use_case.SignInWithFacebookUseCase
import com.a7medkenawy.bloodbank.domain.usecase.register_use_case.sign_in_with_google_use_case.SignInWithGoogleUseCase
import com.a7medkenawy.bloodbank.presentation.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePhoneAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun provideMainActivity(): MainActivity {
        return MainActivity()
    }

    @Provides
    @Singleton
    fun provideUserUseCases(repository: FirebaseRepository): RegisterUseCase {
        return RegisterUseCase(
            phoneNumberUseCase = PhoneNumberUseCase(repository),
            signInWithGoogleUseCase = SignInWithGoogleUseCase(repository),
            signInWithFacebookUseCase = SignInWithFacebookUseCase(repository),
            completeRegistrationUseCase = CompleteRegistrationUseCase(repository)
        )
    }
}