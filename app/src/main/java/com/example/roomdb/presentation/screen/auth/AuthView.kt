package com.example.roomdb.presentation.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.roomdb.presentation.screen.addEditUser.FormTextField
import com.example.roomdb.presentation.screen.auth.component.AuthMode
import com.example.roomdb.presentation.screen.auth.component.AuthUiState
import com.example.roomdb.ui.theme.App_Button

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
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF4D8DFF), Color(0xFFEAF1FF)
                        )
                    )
                ), contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    Surface(
                        shape = CircleShape,
                        color = Color.White,
                        shadowElevation = 8.dp,
                        modifier = Modifier.size(72.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null,
                            tint = Color(0xFF4D8DFF),
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = if (mode == AuthMode.LOGIN) "Welcome Back" else "Create Account",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        elevation = CardDefaults.cardElevation(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(32.dp)
                        ) {

                            /* ---------- NAME (REGISTER ONLY) ---------- */

                            if (mode == AuthMode.REGISTER) {

                                FormTextField(
                                    label = "Full Name *",
                                    value = formState.name,
                                    error = formState.nameError,
                                    placeholder = "e.g., Tejas kanazriya",
                                    onValueChange = { newValue ->
                                        viewModel.onValueChange { state ->
                                            state.copy(name = newValue, nameError = null)
                                        }
                                    })
                            }

                            /* ---------- EMAIL ---------- */

                            FormTextField(
                                label = "Email Address *",
                                value = formState.email,
                                error = formState.emailError,
                                placeholder = "e.g., example@gmail.com",
                                onValueChange = { newValue ->
                                    viewModel.onValueChange { state ->
                                        state.copy(email = newValue, emailError = null)
                                    }
                                })

                            /* ---------- PASSWORD ---------- */

                            FormTextField(
                                label = "Password *",
                                value = formState.password,
                                error = formState.passwordError,
                                placeholder = "Enter your password",
                                onValueChange = { newValue ->
                                    viewModel.onValueChange { state ->
                                        state.copy(password = newValue, passwordError = null)
                                    }
                                },
                                isPasswordField = true
                            )

                            /* ---------- SUBMIT ---------- */

                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { viewModel.submit() },
                                enabled = uiState !is AuthUiState.Loading,
                                colors = ButtonDefaults.buttonColors(containerColor = App_Button),
                                shape = RoundedCornerShape(14.dp)
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

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                HorizontalDivider(modifier = Modifier.weight(1f))
                                Text("or")
                                HorizontalDivider(modifier = Modifier.weight(1f))
                            }

                            /* ---------- SWITCH MODE ---------- */

                            TextButton(
                                onClick = { viewModel.switchMode() },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = if (mode == AuthMode.LOGIN)
                                        "Don't have an account? Register"
                                    else
                                        "Already have an account? Login",
                                    color = MaterialTheme.colorScheme.primary,
                                    textAlign = TextAlign.Center
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
        }
    }
}



