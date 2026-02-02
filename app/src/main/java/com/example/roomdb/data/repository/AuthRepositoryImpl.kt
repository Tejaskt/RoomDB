package com.example.roomdb.data.repository

import com.example.roomdb.presentation.model.FirestoreUser
import com.example.roomdb.presentation.screen.auth.component.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override fun observeAuthState() = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            trySend(firebaseAuth.currentUser != null)
        }

        auth.addAuthStateListener(listener)

        awaitClose {
            auth.removeAuthStateListener(listener)
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): AuthResult = try {

        auth.signInWithEmailAndPassword(email, password).await()

        val uid = auth.currentUser?.uid
            ?: return AuthResult.Error("Authentication failed")

        val snapshot = firestore
            .collection("users")
            .document(uid)
            .get()
            .await()

        val user = snapshot.toObject(FirestoreUser::class.java)
            ?: return AuthResult.Error("User profile not found")

        AuthResult.Success(user)

    } catch (e: Exception) {
        AuthResult.Error(e.message ?: "Login failed")
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): AuthResult = try {

        auth.createUserWithEmailAndPassword(email, password).await()

        val uid = auth.currentUser?.uid
            ?: return AuthResult.Error("Registration failed")

        val user = FirestoreUser(
            uid = uid,
            name = name,
            email = email
        )

        firestore
            .collection("users")
            .document(uid)
            .set(user)
            .await()

        AuthResult.Success(user)

    } catch (e: Exception) {
        AuthResult.Error(e.message ?: "Registration failed")
    }

    override fun logout() {
        auth.signOut()
    }
}

