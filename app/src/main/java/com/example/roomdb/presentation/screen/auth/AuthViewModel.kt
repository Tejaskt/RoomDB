
package com.example.roomdb.presentation.screen.auth

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.repository.AuthRepository
import com.example.roomdb.presentation.screen.auth.component.AuthFormState
import com.example.roomdb.presentation.screen.auth.component.AuthMode
import com.example.roomdb.presentation.screen.auth.component.AuthUiState
import com.example.roomdb.presentation.screen.auth.component.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    /* ---------- MODE ---------- */

    private val _mode = MutableStateFlow(AuthMode.LOGIN)
    val mode: StateFlow<AuthMode> = _mode

    fun switchMode() {
        _mode.value =
            if (_mode.value == AuthMode.LOGIN) AuthMode.REGISTER
            else AuthMode.LOGIN
    }

    /* ---------- FORM STATE ---------- */

    private val _formState = MutableStateFlow(AuthFormState())
    val formState: StateFlow<AuthFormState> = _formState

    fun onValueChange(update: (AuthFormState) -> AuthFormState) {
        _formState.update(update)
    }

    /* ---------- UI STATE ---------- */

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState

    /* ---------- VALIDATION ---------- */

    private fun validate(): Boolean {
        val state = _formState.value

        val nameError =
            if (_mode.value == AuthMode.REGISTER && state.name.isBlank())
                "Name required"
            else null

        val emailError = when {
            state.email.isBlank() -> "Email required"
            !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() ->
                "Invalid email address"
            else -> null
        }

        val passwordError =
            if (state.password.length < 6)
                "Minimum 6 characters required"
            else null

        _formState.value = state.copy(
            nameError = nameError,
            emailError = emailError,
            passwordError = passwordError
        )

        return listOf(nameError, emailError, passwordError).all { it == null }
    }

    /* ---------- LOGIN / REGISTER ---------- */

    fun submit() {
        if (!validate()) return

        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading

            val state = _formState.value

            val result = when (_mode.value) {
                AuthMode.LOGIN ->
                    authRepository.login(state.email, state.password)

                AuthMode.REGISTER ->
                    authRepository.register(
                        state.name,
                        state.email,
                        state.password
                    )
            }

            _uiState.value = when (result) {
                is AuthResult.Success -> AuthUiState.Success
                is AuthResult.Error -> AuthUiState.Error(result.message)
                is AuthResult.Loading -> AuthUiState.Loading
            }
        }
    }

    /* ---------- LOGOUT ---------- */

    fun logout() {
        authRepository.logout()
        // Navigation handled by Splash via observeAuthState()
    }
}



