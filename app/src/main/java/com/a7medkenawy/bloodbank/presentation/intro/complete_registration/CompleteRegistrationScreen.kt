package com.a7medkenawy.bloodbank.presentation.intro.complete_registration

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.a7medkenawy.bloodbank.R
import com.a7medkenawy.bloodbank.databinding.FragmentCompleteRestrationScreenBinding
import com.a7medkenawy.bloodbank.domain.model.user.User
import com.a7medkenawy.bloodbank.utils.Constants.female
import com.a7medkenawy.bloodbank.utils.Constants.male
import com.a7medkenawy.bloodbank.utils.TypesOfRegistration
import com.google.android.material.chip.Chip
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import java.io.File
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class CompleteRegistrationScreen : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var binding: FragmentCompleteRestrationScreenBinding

    private var name: String = ""
    private var image: String = ""
    private var email: String = ""
    private var phoneNumber: String = ""
    private var governorate: String = ""
    private var gender: String = male
    private var bloodType: String = "A+"


    @Inject
    lateinit var auth: FirebaseAuth

    private lateinit var completeRegistrationViewModel: CompleteRegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCompleteRestrationScreenBinding.inflate(inflater, container, false)
        completeRegistrationViewModel =
            ViewModelProvider(requireActivity())[CompleteRegistrationViewModel::class.java]

        binding.nameEditText.requestFocus()
        binding.emailEditText.requestFocus()
        binding.phoneNumberEditText.requestFocus()

        binding.spinner.onItemSelectedListener = this
        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent()) {
            binding.userIv.setImageURI(it)
            completeRegistrationViewModel.uploadImageToCloudStorage(requireActivity(), it!!)
        }
        binding.userIv.setOnClickListener {
            getImage.launch("image/*")

        }
        handleAllRegistrationState()
        setUpChipGroup()
        handleGenderType()
        onPressButtonDone()
        return binding.root
    }


    private fun handleAllRegistrationState() {
        if (registerWithGoogle()) {
            fillFieldsWithDataIfUsedFacebookOrGoogle()
        } else if (registerWithOtp()) {
            setPhoneNumberWhenUserUsingOtp()
        } else if (registerWithFacebook()) {
            fillFieldsWithDataIfUsedFacebookOrGoogle()
        }
    }


    private fun onPressButtonDone() {
        binding.compRegDone.setOnClickListener {
            getAllParameters()
            if (TextUtils.isEmpty(binding.nameEditText.text))
                binding.nameEditText.error = "please enter your name."
            else if (TextUtils.isEmpty(binding.emailEditText.text))
                binding.emailEditText.error = "please enter your email."
            else if (TextUtils.isEmpty(binding.phoneNumberEditText.text))
                binding.phoneNumberEditText.error = "please enter your Phone Number."
            else {
                val user = getUserDetails()
                completeRegistrationViewModel.registerUser(user)
                moveIfRegistrationDoneToHomeScreen()
            }
        }
    }

    private fun moveIfRegistrationDoneToHomeScreen() {
        lifecycleScope.launchWhenCreated {
            completeRegistrationViewModel.isRegister.collect {
                if (it) {
                    binding.compRegDone.visibility = View.INVISIBLE
                    binding.progressBar.visibility = View.VISIBLE
                    findNavController().navigate(R.id.action_completeRegistrationScreen_to_homeScreen)
                } else {
                    binding.compRegDone.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun getAllParameters() {
        image = completeRegistrationViewModel.imageUrl.value
        phoneNumber = binding.phoneNumberEditText.text.toString()
        name = binding.nameEditText.text.toString()
        email = binding.emailEditText.text.toString()
    }

    private fun getUserDetails(): User {
        return User(auth.currentUser!!.uid,
            name,
            image,
            email,
            phoneNumber,
            governorate,
            gender,
            bloodType,
            true
        )
    }

    private fun fillFieldsWithDataIfUsedFacebookOrGoogle() {
        setUpNameEditText()
        setUpEmailEditText()

    }

    private fun setPhoneNumberWhenUserUsingOtp() {
        phoneNumber = completeRegistrationViewModel.phoneNumber.value
        binding.phoneNumberEditText.setText(phoneNumber)
        binding.phoneNumberEditText.isEnabled = false
    }


    private fun setUpEmailEditText() {
        if (auth.currentUser?.email != null) {
            email = auth.currentUser?.email.toString()
            binding.emailEditText.setText(email)
            binding.emailEditText.isEnabled = false
        }
    }


    private fun setUpNameEditText() {
        if (auth.currentUser?.displayName != null) {
            name = auth.currentUser!!.displayName.toString()
            binding.nameEditText.setText(name)
            binding.nameEditText.isEnabled = false
        }
    }


    //get blood type
    private fun setUpChipGroup() {
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            bloodType = chip.text.toString().uppercase(Locale.ROOT)
        }
    }

    // get gender type
    private fun handleGenderType() {
        binding.compRegRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.male_rb -> {
                    gender = male
                }
                R.id.female_rb -> {
                    gender = female
                }
            }
        }
    }

    //check if register with phone number
    private fun registerWithOtp() =
        completeRegistrationViewModel.registrationType.value == TypesOfRegistration.OTP.toString()

    //check if register with google account
    private fun registerWithGoogle() =
        completeRegistrationViewModel.registrationType.value == TypesOfRegistration.GOOGLE.toString()

    //check if register with facebook
    private fun registerWithFacebook() =
        completeRegistrationViewModel.registrationType.value == TypesOfRegistration.FACEBOOK.toString()


    // spinner (when item selected)
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        governorate = parent?.getItemAtPosition(position).toString()
    }

    // spinner (when Nothing selected)
    override fun onNothingSelected(parent: AdapterView<*>?) {
        governorate = "Cairo"
    }
}