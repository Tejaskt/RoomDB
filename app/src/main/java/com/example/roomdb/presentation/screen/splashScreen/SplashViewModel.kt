package com.example.roomdb.presentation.screen.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.repository.AuthRepository
import com.example.roomdb.presentation.screen.splashScreen.component.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    authRepository: AuthRepository
) : ViewModel() {


    /*------- CHECKS WHETHER USER IS LOGGED IN OR NOT -------*/
    val splashState = authRepository
        .observeAuthState()
        .map { isLoggedIn ->
            if (isLoggedIn) SplashState.Authenticated
            else SplashState.Unauthenticated
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = SplashState.Loading
        )
}
