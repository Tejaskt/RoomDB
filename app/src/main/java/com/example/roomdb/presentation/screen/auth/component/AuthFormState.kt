package com.example.roomdb.presentation.screen.auth.component

data class AuthFormState(
    val name : String = "",
    val email : String = "",
    val password : String = "",
    val nameError : String? = null,
    val emailError : String? = null,
    val passwordError : String? = null
)