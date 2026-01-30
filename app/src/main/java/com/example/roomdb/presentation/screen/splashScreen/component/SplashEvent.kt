package com.example.roomdb.presentation.screen.splashScreen.component

sealed class SplashEvent {
    object NavigateToAuth : SplashEvent()
    object NavigateToDashboard : SplashEvent()
}