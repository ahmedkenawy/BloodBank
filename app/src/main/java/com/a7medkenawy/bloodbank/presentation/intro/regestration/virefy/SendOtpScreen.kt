package com.a7medkenawy.bloodbank.presentation.intro.regestration.virefy

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a7medkenawy.bloodbank.R
import com.a7medkenawy.bloodbank.databinding.FragmentSendOtpScreenBinding
import kotlinx.android.synthetic.main.fragment_send_otp_screen.*


class SendOtpScreen : Fragment() {
    private lateinit var binding: FragmentSendOtpScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSendOtpScreenBinding.inflate(inflater, container, false)

        setUpOTPInput()
        return binding.root
    }

    private fun setUpOTPInput() {
        binding.input1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (input1.text.toString().trim().isNotEmpty()) {
                    binding.input2.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding.input2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (input2.text.toString().trim().isNotEmpty()) {
                    binding.input3.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.input3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (input3.text.toString().trim().isNotEmpty()) {
                    binding.input4.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.input4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (input4.text.toString().trim().isNotEmpty()) {
                    binding.input5.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.input5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (input5.text.toString().trim().isNotEmpty()) {
                    binding.input6.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

}