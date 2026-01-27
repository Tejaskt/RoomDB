package com.example.roomdb.presentation.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomdb.R
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.presentation.screen.dashboard.DashboardViewModel
import com.example.roomdb.presentation.screen.detail.components.InfoCard
import com.example.roomdb.presentation.screen.detail.components.InfoRow
import com.example.roomdb.presentation.screen.detail.components.UserAvatar
import com.example.roomdb.presentation.utils.AppScaffold
import com.example.roomdb.presentation.utils.AppTopBar
import com.example.roomdb.presentation.utils.LoadingView
import com.example.roomdb.presentation.utils.UiState

@OptIn(ExperimentalMaterial3Api::class)
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
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_arrow),
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
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState()),
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
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    InfoCard {
                        InfoRow(Icons.Outlined.Email, "Email", user.email)
                        HorizontalDivider()
                        InfoRow(Icons.Outlined.Person, "Age", user.age.toString())
                        HorizontalDivider()
                        InfoRow(Icons.Outlined.LocationOn, "College", user.college)
                        HorizontalDivider()
                        InfoRow(Icons.Outlined.PlayArrow, "Stream", user.stream)
                    }
                }
            }
        }
    }
}

// sample
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevUserDetailView(
    onBack: () -> Unit = {}
) {

    AppScaffold(
        topBarContent = {
            AppTopBar(
                title = "User Details",
                icon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_arrow)
                        )
                    }
                }
            )
        }
    ) { padding ->

                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(24.dp))

                    UserAvatar(
                        initials = "Tejas"
                            .split(" ")
                            .take(2)
                            .joinToString("") { it.first().toString() }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Tejas",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    InfoCard {
                        InfoRow(Icons.Default.Email, "Email", "Tejaskt@gmail.com")

                        HorizontalDivider(thickness = 2.dp)

                        InfoRow(Icons.Default.Person, "Age", "24") // replace with phone later

                        HorizontalDivider(thickness = 2.dp)

                        InfoRow(
                            Icons.Default.LocationOn,
                            "Member Since",
                            "January 23, 2026"
                        )
                    }
                }
            }
        }