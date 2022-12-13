package com.a7medkenawy.bloodbank.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.a7medkenawy.bloodbank.R
import com.a7medkenawy.bloodbank.utils.Constants.callBackManager
import com.facebook.FacebookSdk
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        FacebookSdk.sdkInitialize(this@MainActivity)
        supportActionBar?.hide()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            loginWithGoogleResult(data)
        } else {
            loginWithFacebookResult(requestCode, resultCode, data)
        }
    }

    private fun loginWithFacebookResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        callBackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun loginWithGoogleResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        val account = task.getResult(ApiException::class.java)
        if (task.isSuccessful) {
            firebaseAuthWithGoogle(account.idToken)
        } else {
            Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                findNavController(com.a7medkenawy.bloodbank.R.id.fragmentContainerView).navigate(com.a7medkenawy.bloodbank.R.id.action_registerationScreen_to_completeRegistrationScreen)
            }
            .addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
    }

    fun gotoCompleteRegistrationActivity() {
        findNavController(R.id.fragmentContainerView)
            .navigate(R.id.action_registerationScreen_to_completeRegistrationScreen)
    }
}
