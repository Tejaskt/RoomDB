package com.example.roomdb.presentation.screen.remoteUsers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.roomdb.presentation.model.RemoteUser
import com.example.roomdb.presentation.screen.remoteUsers.components.SyncState
import com.example.roomdb.presentation.utils.AppScaffold
import com.example.roomdb.presentation.utils.AppTopBar
import com.example.roomdb.presentation.utils.ScreenSpace

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RemoteUserScreen(viewModel: RemoteUsersViewModel = hiltViewModel()) {

    val users by viewModel.users.collectAsState()
    val syncState by viewModel.syncState.collectAsState()

    // for refresh.
    val isRefreshing = syncState is SyncState.Syncing
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { viewModel.sync() }
    )

    AppScaffold(
        topBarContent = {
            AppTopBar(
                title = "Remote Users",
                subtitle = "Fetch From JsonPlaceholder Api"
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
                .padding(paddingValues)
        ){
            UserList(users)

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )

            when(syncState){
                is SyncState.Syncing -> {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                is SyncState.Error -> {
                    if (users.isEmpty()){
                        Text(
                            text = (syncState as SyncState.Error).message,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(56.dp)
                        )
                    }
                }

                else -> Unit

            }
        }

        /* not to do this because sync in not ui state it's a network state so manage it differently.
        when(state){
            is UiState.Loading -> {
                LoadingView()
            }
            is UiState.Error -> {
                ErrorView(
                    message = (state as UiState.Error).message,
                    onRetry = { viewModel.fetchUsers() }
                )
            }
            is UiState.Success -> {
                UserList(
                    users = (state as UiState.Success<List<RemoteUser>>).data
                )
            }
        } */

    }
}

@Composable
private fun UserList(
    users: List<RemoteUser>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),//.windowInsetsPadding(WindowInsets.displayCutout),
        contentPadding = PaddingValues(ScreenSpace.Horizontal_Space),
        verticalArrangement = Arrangement.spacedBy(ScreenSpace.Vertical_Space)
    ) {
        items(users) { user ->
            UserItem(user)
        }
    }
}

@Composable
private fun UserItem(user: RemoteUser) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(ScreenSpace.Horizontal_Space)) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = user.email,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun ErrorView(
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = message)
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onRetry) {
                Text("Retry")
            }
        }
    }
}

/* Flow
* UI
   ↓
* RemoteUsersViewModel
   ↓
* RemoteUserRepository
   ↓
* Retrofit API
   ↓
* DTO
   ↓
* Mapper
   ↓
* Entity
   ↓
* Room
   ↓
* Flow
   ↓
* UI
* */