package com.example.roomdb.presentation.screen.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.roomdb.presentation.screen.auth.component.*

@Composable
fun AuthView(
    onNavigateToDashboard: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()

) {
    val mode by viewModel.mode.collectAsState()
    val formState by viewModel.formState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    /* ---------- AUTO NAVIGATION ---------- */

    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Success) {
            onNavigateToDashboard()
        }
    }

    Scaffold { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(0.9f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = if (mode == AuthMode.LOGIN) "Login" else "Register",
                    style = MaterialTheme.typography.headlineMedium
                )

                /* ---------- NAME (REGISTER ONLY) ---------- */

                if (mode == AuthMode.REGISTER) {
                    OutlinedTextField(
                        value = formState.name,
                        onValueChange = {
                            viewModel.onValueChange { state ->
                                state.copy(name = it, nameError = null)
                            }
                        },
                        label = { Text("Name") },
                        isError = formState.nameError != null,
                        modifier = Modifier.fillMaxWidth()
                    )

                    formState.nameError?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                }

                /* ---------- EMAIL ---------- */

                OutlinedTextField(
                    value = formState.email,
                    onValueChange = {
                        viewModel.onValueChange { state ->
                            state.copy(email = it, emailError = null)
                        }
                    },
                    label = { Text("Email") },
                    isError = formState.emailError != null,
                    modifier = Modifier.fillMaxWidth()
                )
                formState.emailError?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }

                /* ---------- PASSWORD ---------- */

                OutlinedTextField(
                    value = formState.password,
                    onValueChange = {
                        viewModel.onValueChange { state ->
                            state.copy(password = it, passwordError = null)
                        }
                    },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = formState.passwordError != null,
                    modifier = Modifier.fillMaxWidth()
                )
                formState.passwordError?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }

                /* ---------- SUBMIT ---------- */

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.submit() },
                    enabled = uiState !is AuthUiState.Loading
                ) {
                    if (uiState is AuthUiState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            if (mode == AuthMode.LOGIN) "Login" else "Register"
                        )
                    }
                }

                /* ---------- SWITCH MODE ---------- */

                TextButton(
                    onClick = { viewModel.switchMode() }
                ) {
                    Text(
                        if (mode == AuthMode.LOGIN)
                            "Don't have an account? Register"
                        else
                            "Already have an account? Login"
                    )
                }

                /* ---------- ERROR ---------- */

                if (uiState is AuthUiState.Error) {
                    Text(
                        text = (uiState as AuthUiState.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

