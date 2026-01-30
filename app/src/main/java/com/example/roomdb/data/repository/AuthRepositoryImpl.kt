package com.example.roomdb.data.repository

import com.example.roomdb.presentation.utils.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): AuthResult {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email,password)
                .await()
            AuthResult.Success(result.user?.uid ?: "")
        }catch (e : Exception){
            AuthResult.Error(e.message ?: "Login Failed")
        }
    }

    override suspend fun register(
        email: String,
        password: String
    ): AuthResult {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password)
                .await() // coroutine-friendly Firebase

            AuthResult.Success(result.user?.uid ?: "")
        }catch (e : Exception){
            AuthResult.Error(e.message ?: "Registration Failed")
        }
    }

    override fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}