package com.a7medkenawy.bloodbank.data.repository

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.a7medkenawy.bloodbank.domain.model.user.User
import com.a7medkenawy.bloodbank.domain.repository.FirebaseRepository
import com.a7medkenawy.bloodbank.presentation.MainActivity
import com.a7medkenawy.bloodbank.presentation.intro.complete_registration.CompleteRegistrationViewModel
import com.a7medkenawy.bloodbank.presentation.intro.regestration.sgininwithphonenumber.VerifyViewModel
import com.a7medkenawy.bloodbank.utils.Constants.USERS
import com.a7medkenawy.bloodbank.utils.Constants.callBackManager
import com.a7medkenawy.bloodbank.utils.Constants.getImageExtension
import com.a7medkenawy.bloodbank.utils.TypesOfRegistration
import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FirebaseRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,

    ) : FirebaseRepository {


    override suspend fun signInWithPhoneNumber(
        phoneNumber: String,
        activity: Activity,
        viewModel: VerifyViewModel,
    ) {
        if (phoneNumber.trim().isNotEmpty()) {
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(activity)                 // Activity (for callback binding)
                .setCallbacks(callbacks(viewModel))          // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        } else {
            Toast.makeText(activity.applicationContext, "Fields cannot be empty", Toast.LENGTH_LONG)
                .show()
        }


    }

    private fun callbacks(viewModel: VerifyViewModel): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(e: FirebaseException) {

                if (e is FirebaseAuthInvalidCredentialsException) {
                    Log.d("KENO", "onVerificationFailed: " + e.message)
                } else if (e is FirebaseTooManyRequestsException) {
                    Log.d("KENO", "onVerificationFailed: " + e.message)
                }

                // Show a message and update the UI
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken,
            ) {
                viewModel.sendVerificationCode(verificationId)
            }
        }
        return callbacks
    }

    override suspend fun signInWithGoogle(activity: Activity, serverClient: String) {
        //google signIn Configuration
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(serverClient)
            .requestEmail()
            .build()
        val gsc = GoogleSignIn.getClient(activity, gso)

        signIn(gsc, activity)
    }

    override suspend fun signInWithFacebook(activity: Activity) {
        LoginManager.getInstance()
            .logInWithReadPermissions(activity, listOf("email", "public_profile"))
        LoginManager.getInstance()
            .registerCallback(callBackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
//                Toast.makeText(activity,"onSuccess",Toast.LENGTH_LONG).show()
                    handleFacebookAccessToken(activity as MainActivity, loginResult.accessToken)
                }

                override fun onCancel() {
                    Toast.makeText(activity, "onCancel", Toast.LENGTH_LONG).show()
                    Log.d("KENO", "facebook:onCancel")
                }

                override fun onError(error: FacebookException) {
                    Toast.makeText(activity, "onError", Toast.LENGTH_LONG).show()
                    Log.d("KENO", "facebook:onError", error)
                }
            })
    }

    override suspend fun registerUser(
        user: User,
        completeRegistrationViewModel: CompleteRegistrationViewModel,
    ) {
        fireStore.collection(USERS)
            .document(user.id)
            .set(user)
            .addOnSuccessListener {
                completeRegistrationViewModel.setIsRegister(true)
            }
            .addOnFailureListener {
                completeRegistrationViewModel.setIsRegister(false)
            }
    }

    override suspend fun uploadImageToCloudStorage(
        activity: Activity,
        imageUri: Uri,
        completeRegistrationViewModel: CompleteRegistrationViewModel,
    ) {
        val fRef = firebaseStorage.reference.child(
            "profileImages/${auth.currentUser!!.uid}.${System.currentTimeMillis()}")
        fRef.putFile(imageUri).addOnSuccessListener { taskSnapsShot ->
            taskSnapsShot.metadata!!.reference!!.downloadUrl
                .addOnSuccessListener {
                    completeRegistrationViewModel.setImageUrl(it.toString())
                }
        }.addOnFailureListener {

        }
    }

    //signIn with Google
    private fun signIn(gsc: GoogleSignInClient, activity: Activity) {
        val signInIntent: Intent = gsc.signInIntent
        activity.startActivityForResult(signInIntent, 100)
    }

    private fun handleFacebookAccessToken(activity: MainActivity, token: AccessToken) {
        var completeRegistrationViewModel =
            ViewModelProvider(activity)[CompleteRegistrationViewModel::class.java]
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    completeRegistrationViewModel
                        .returnRegistrationType(TypesOfRegistration.FACEBOOK.toString())
                    activity.gotoCompleteRegistrationActivity()
                } else {
                    Toast.makeText(activity,
                        "task is not successful ${task.exception!!.message}",
                        Toast.LENGTH_LONG).show()
                }
            }
    }

}