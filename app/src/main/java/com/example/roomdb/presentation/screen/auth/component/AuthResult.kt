package com.example.roomdb.presentation.screen.auth.component

import com.example.roomdb.presentation.model.FirestoreUser

sealed class AuthResult {
    object Loading : AuthResult()
    data class Success(val userId: FirestoreUser) : AuthResult()
    data class Error(val message : String) : AuthResult()
}