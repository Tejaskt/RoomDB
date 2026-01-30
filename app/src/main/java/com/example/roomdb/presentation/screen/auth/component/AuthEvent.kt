package com.example.roomdb.presentation.screen.auth.component

sealed class AuthEvent {
    object NavigateToDashboard : AuthEvent()
    object NavigateToLogin: AuthEvent()
}