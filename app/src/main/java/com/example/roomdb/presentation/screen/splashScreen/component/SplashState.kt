package com.example.roomdb.presentation.screen.splashScreen.component

sealed class SplashState {
    object Loading : SplashState()
    object Authenticated : SplashState()
    object Unauthenticated : SplashState()
}
