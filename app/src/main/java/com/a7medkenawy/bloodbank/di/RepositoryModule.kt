package com.a7medkenawy.bloodbank.di

import com.a7medkenawy.bloodbank.data.repository.FirebaseRepositoryImpl
import com.a7medkenawy.bloodbank.domain.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
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
class RepositoryModule {

    @Singleton
    @Provides
    fun provideFirebaseRepository(
        auth: FirebaseAuth,
        fireStore: FirebaseFirestore,
        firebaseStorage: FirebaseStorage,
    ): FirebaseRepository {
        return FirebaseRepositoryImpl(
            auth,
            fireStore,
            firebaseStorage
        )
    }
}