package com.example.roomdb.presentation.screen.auth

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.repository.AuthRepository
import com.example.roomdb.presentation.screen.auth.component.AuthEvent
import com.example.roomdb.presentation.screen.auth.component.AuthFormState
import com.example.roomdb.presentation.screen.auth.component.AuthMode
import com.example.roomdb.presentation.screen.auth.component.AuthUiState
import com.example.roomdb.presentation.screen.auth.component.AuthUiState.Error
import com.example.roomdb.presentation.screen.auth.component.AuthUiState.Loading
import com.example.roomdb.presentation.utils.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel(){

    /* ---------- MODES ---------- */

    private val _mode = MutableStateFlow(AuthMode.LOGIN)
    val mode : StateFlow<AuthMode> = _mode

    // ui logic to change modes
    fun switchMode(){
        _mode.value = if (_mode.value == AuthMode.LOGIN) AuthMode.REGISTER else AuthMode.LOGIN
    }

    /* ---------- FORM STATE ---------- */

    private val _formState = MutableStateFlow(AuthFormState())
    val formState: StateFlow<AuthFormState> = _formState

    fun onValueChange(value: (AuthFormState) -> AuthFormState) {
        _formState.update(value)
    }

    /* ---------- UI STATE ---------- */

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState : StateFlow<AuthUiState> = _uiState

    /* ---------- EVENT FLOW ---------- */

    private val _eventFlow = MutableSharedFlow<AuthEvent>()
    val eventFlow  = _eventFlow.asSharedFlow()

    // checks user login or not
    init {
        if(authRepository.isUserLoggedIn()){
            viewModelScope.launch {
                _eventFlow.emit(AuthEvent.NavigateToDashboard)
            }
        }
    }

    /* ---------- VALIDATION ---------- */

    private fun validate() : Boolean{
        val state = _formState.value

        val emailError = when{
            state.email.isBlank() -> "Email Required"
            !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> "Invalid Email address"
            else -> null
        }

        val passwordError = if(state.password.length < 6) "Min 6 Character required" else null

        _formState.value = state.copy(
            emailError = emailError,
            passwordError = passwordError
        )

        return emailError == null && passwordError == null
    }

    /* ---------- LOGIN & REGISTER ---------- */

    fun submit() {

        if (!validate()) return

        viewModelScope.launch {

            _uiState.value = Loading

            val email = _formState.value.email
            val password = _formState.value.password

            val result = when(_mode.value) {
                AuthMode.LOGIN -> authRepository.login(email,password)
                AuthMode.REGISTER -> authRepository.register(email,password)
            }

            when (result) {
                is AuthResult.Success -> {
                    _uiState.value = AuthUiState.Success
                    _eventFlow.emit(AuthEvent.NavigateToDashboard)
                }
                is AuthResult.Error -> {
                    _uiState.value = Error(result.message)
                }

                else -> Unit
            }
        }
    }

    // Checks if user logged in or not.
    fun isLoggedIn() : Boolean {
        return authRepository.isUserLoggedIn()
    }

    /* ---------- LOGOUT ---------- */

    fun logout(){
        authRepository.logout()
        viewModelScope.launch {
            _eventFlow.emit(AuthEvent.NavigateToLogin)
        }
    }

}