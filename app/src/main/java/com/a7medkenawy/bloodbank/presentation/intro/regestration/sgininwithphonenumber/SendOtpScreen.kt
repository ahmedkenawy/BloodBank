package com.a7medkenawy.bloodbank.presentation.intro.regestration.sgininwithphonenumber

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.a7medkenawy.bloodbank.R
import com.a7medkenawy.bloodbank.databinding.FragmentSendOtpScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendOtpScreen : Fragment() {
    private lateinit var binding: FragmentSendOtpScreenBinding
    private lateinit var viewModel: VerifyViewModel
    val args: SendOtpScreenArgs by navArgs()
    private var verificationId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSendOtpScreenBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[VerifyViewModel::class.java]
        binding.otpScreenPhoneNumber.text = args.phoneNumber
        viewModel.verificationId.asLiveData().observe(viewLifecycleOwner) { verificationId ->
            this.verificationId = verificationId
        }


        binding.verifyBtn.setOnClickListener {
            loginUsingPhoneNumber()
        }

        binding.otpScreenBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_sendOtpScreen_to_verifyMobileNumberScreen)
        }

        setUpOTPInput()
        return binding.root
    }

    private fun loginUsingPhoneNumber() {
        if (viewModel.verificationId.value != null) {
            binding.verifyBtn.visibility = View.INVISIBLE
            binding.verifyOtpProgressbar.visibility = View.VISIBLE
            val phoneAuthCredential = checkOTP()
            verifyOTP(phoneAuthCredential)
        }
    }

    private fun checkOTP(): PhoneAuthCredential {
        return PhoneAuthProvider.getCredential(
            viewModel.verificationId.value, code()
        )
    }

    private fun verifyOTP(phoneAuthCredential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_sendOtpScreen_to_homeScreen)
                } else {
                    binding.verifyBtn.visibility = View.VISIBLE
                    binding.verifyOtpProgressbar.visibility = View.INVISIBLE
                    setAlertDialogError()
                }
            }
    }

    private fun setAlertDialogError() {
        AlertDialog.Builder(requireContext())
            .setTitle("Registration Failed")
            .setMessage("some errors happens maybe code invalid or no internet connection,try again")
            .setIcon(R.drawable.ic_warning)
            .create().show()
    }

    private fun code(): String {
        return binding.input1.text.toString() +
                binding.input2.text.toString() +
                binding.input3.text.toString() +
                binding.input4.text.toString() +
                binding.input5.text.toString() +
                binding.input6.text.toString()
    }

    private fun setUpOTPInput() {
        binding.input1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.input1.text.toString().trim().isNotEmpty()) {
                    binding.input2.requestFocus()
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding.input2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.input2.text.toString().trim().isNotEmpty()) {
                    binding.input3.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.input3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.input3.text.toString().trim().isNotEmpty()) {
                    binding.input4.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.input4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.input4.text.toString().trim().isNotEmpty()) {
                    binding.input5.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.input5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.input5.text.toString().trim().isNotEmpty()) {
                    binding.input6.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

}