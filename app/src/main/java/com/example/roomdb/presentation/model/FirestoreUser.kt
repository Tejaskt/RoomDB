package com.example.roomdb.presentation.model

data class FirestoreUser(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
