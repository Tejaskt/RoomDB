package com.example.roomdb.presentation.screen.auth.component

data class AuthFormState(
    val email : String = "",
    val password : String = "",
    val emailError : String? = null,
    val passwordError : String? = null
)