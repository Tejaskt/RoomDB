package com.example.roomdb.presentation.screen.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.repository.AuthRepository
import com.example.roomdb.presentation.screen.splashScreen.component.SplashEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel(){

    private val _eventFlow = MutableSharedFlow<SplashEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(1000)

            if (authRepository.isUserLoggedIn()){
                _eventFlow.emit(SplashEvent.NavigateToDashboard)
            }else{
                _eventFlow.emit(SplashEvent.NavigateToAuth)
            }
        }
    }
}