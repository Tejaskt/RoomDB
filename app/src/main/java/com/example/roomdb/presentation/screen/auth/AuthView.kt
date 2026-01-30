package com.example.roomdb.presentation.screen.auth

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.roomdb.presentation.screen.auth.component.AuthEvent
import com.example.roomdb.presentation.screen.auth.component.AuthMode
import com.example.roomdb.presentation.screen.auth.component.AuthUiState
import com.example.roomdb.presentation.utils.LoadingView

@Composable
fun AuthView(
    viewModel : AuthViewModel = hiltViewModel()
) {

    val formState by viewModel.formState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val mode by viewModel.mode.collectAsState()

    when(uiState){
        is AuthUiState.Loading -> LoadingView()
        is AuthUiState.Success -> {
            TextButton(onClick =  viewModel::switchMode) {
                Text(text =
                    if (mode == AuthMode.LOGIN) "Don't have an account? Register"
                    else "Already have an account? Login"
                )
            }
        }
        is AuthUiState.Error -> Text((uiState as AuthUiState.Error).message)
        else -> Unit
    }
}