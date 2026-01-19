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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.roomdb.presentation.model.RemoteUser
import com.example.roomdb.presentation.utils.LoadingView
import com.example.roomdb.presentation.screen.RemoteUsersViewModel
import com.example.roomdb.presentation.utils.UiState

@Composable
fun RemoteUserScreen(viewModel: RemoteUsersViewModel) {
    val state by viewModel.usersState.collectAsState()

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
    }

}

@Composable
private fun UserList(
    users: List<RemoteUser>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
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
        Column(modifier = Modifier.padding(16.dp)) {
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
