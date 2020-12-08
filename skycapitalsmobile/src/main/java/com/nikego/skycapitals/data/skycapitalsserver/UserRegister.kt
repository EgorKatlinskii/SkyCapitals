package com.nikego.skycapitals.data.skycapitalsserver


data class UserRegister(
    val userName: String,
    val userSurname: String,
    val password: Int,
    val ostOffice: String
)