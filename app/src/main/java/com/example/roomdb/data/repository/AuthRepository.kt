package com.example.roomdb.data.repository

import com.example.roomdb.presentation.screen.auth.component.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun observeAuthState(): Flow<Boolean>

    suspend fun login(
        email: String,
        password: String
    ): AuthResult

    suspend fun register(
        name: String,
        email: String,
        password: String
    ): AuthResult

    fun logout()
}
