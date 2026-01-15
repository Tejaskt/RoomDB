package com.example.roomdb.presentation.screen.addEditUser.util

data class UserFormState(
    val name: String = "",
    val email: String = "",
    val age: String = "",
    val college: String = "",
    val stream: String = "",

    val nameError: String? = null,
    val emailError: String? = null,
    val ageError: String? = null,
    val collegeError: String? = null,
    val streamError: String? = null
)
