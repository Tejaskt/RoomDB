package com.example.roomdb.presentation.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.roomdb.R
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.presentation.screen.dashboard.DashboardViewModel
import com.example.roomdb.presentation.screen.detail.components.*
import com.example.roomdb.presentation.utils.*

@Composable
fun UserDetailView(
    viewModel: DashboardViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.selectedUserState.collectAsState()

    AppScaffold(
        topBarContent = {
            AppTopBar(
                title = "User Details",
                icon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_arrow)
                        )
                    }
                }
            )
        }
    ) { padding ->

        when (state) {

            is UiState.Loading -> LoadingView()

            is UiState.Error -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = (state as UiState.Error).message)
            }

            is UiState.Success -> {
                val user = (state as UiState.Success<User>).data

                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(24.dp))

                    UserAvatar(
                        initials = user.name
                            .split(" ")
                            .take(2)
                            .joinToString("") { it.first().toString() }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    Spacer(modifier = Modifier.height(24.dp))

                    InfoCard {
                        InfoRow(Icons.Default.Email, "Email", user.email)
                        InfoRow(Icons.Default.Phone, "Phone", user.age) // replace with phone later
                        InfoRow(
                            Icons.Default.LocationOn,
                            "Member Since",
                            "January 23, 2026"
                        )
                    }
                }
            }
        }
    }
}
