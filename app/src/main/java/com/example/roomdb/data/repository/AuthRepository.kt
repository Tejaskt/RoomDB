package com.example.roomdb.data.repository

import com.example.roomdb.presentation.utils.AuthResult

interface AuthRepository {

    suspend fun login(
        email : String,
        password : String
    ): AuthResult

    suspend fun register(
        email: String,
        password: String
    ): AuthResult

    fun isUserLoggedIn() : Boolean

    fun logout()
}