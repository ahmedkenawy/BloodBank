package com.a7medkenawy.bloodbank.domain.model.user

import java.util.*

data class User(
    val id: String,
    val name: String,
    val image: String,
    val email: String,
    val phoneNumber: String,
    val governorate: String,
    val gender: String,
    val bloodType: String,
)