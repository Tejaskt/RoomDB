package com.example.roomdb.presentation.utils

sealed class AuthResult {
    object Loading : AuthResult()
    data class Success(val userId : String) : AuthResult()
    data class Error(val message : String) : AuthResult()
}