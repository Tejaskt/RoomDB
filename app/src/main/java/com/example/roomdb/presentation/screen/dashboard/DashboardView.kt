package com.example.roomdb.presentation.screen.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.presentation.utils.AppScaffold
import com.example.roomdb.presentation.utils.AppTopBar
import com.example.roomdb.presentation.utils.LoadingView
import com.example.roomdb.presentation.utils.ScreenSpace
import com.example.roomdb.presentation.utils.UiEvent
import com.example.roomdb.presentation.utils.UiState
import com.example.roomdb.ui.theme.ICON_Red
import com.example.roomdb.ui.theme.PurpleGrey40
import com.example.roomdb.ui.theme.User_Avatar

//@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(
    viewModel: DashboardViewModel,
    onAddUserClick: () -> Unit,
    onUserDetailsClick: (Int) -> Unit,
    onEditUserClick: (Int) -> Unit,
    onRemoteUsersClick: () -> Unit // for api fetch
) {

    val state by viewModel.usersState.collectAsState()

    val userCount by viewModel.userCount.collectAsState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is UiEvent.ShowUndoDelete -> {
                    val result = snackBarHostState.showSnackbar(
                        message = "User Deleted",
                        actionLabel = "UNDO"
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.undoDelete(event.user)
                    }
                }
            }
        }
    }

    AppScaffold(

        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        snackbarHost = { SnackbarHost(snackBarHostState) },

        floatingActionButton = {
            FloatingActionButton(onClick = onAddUserClick, shape = FloatingActionButtonDefaults.largeShape) {
                Icon(Icons.Outlined.Add, contentDescription = "Add Icon")
            }
        },

        topBarContent = {
            AppTopBar(
                title = "Users",
                subtitle = "$userCount total users",
                scrollBehavior = scrollBehavior
            )
        }

    ) { paddingValues ->

        when (state) {
            is UiState.Loading -> LoadingView()

            is UiState.Error -> Text("Something went wrong")

            is UiState.Success -> {

                val users = (state as UiState.Success<List<User>>).data

                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues),
                    contentPadding = PaddingValues(ScreenSpace.Horizontal_Space)
                ) {
                    items(users) { user ->
                        UserCard(onUserClick = onUserDetailsClick,user = user,onEditUserClick = onEditUserClick, onDeleteUserClick = { viewModel.deleteUser(user) })
                    }
                }
            }
        }
    }
}

@Composable
fun UserCard(
   onUserClick : (Int) -> Unit,
   user : User,
   onEditUserClick: (Int) -> Unit,
   onDeleteUserClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{onUserClick(user.id)}
            .padding(vertical = 6.dp),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Avatar needs to be change.
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                color = User_Avatar,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = user.name.trim()
                            .split("\\s+".toRegex())
                            .take(2)
                            .joinToString("") { it.first().uppercaseChar().toString() }
                        ,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // User info
            Column(modifier = Modifier.weight(1f)) {
                Text(text = user.name, style = MaterialTheme.typography.titleMedium)
                Text(text = user.email, style = MaterialTheme.typography.bodySmall)
            }


            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = {onEditUserClick(user.id)}) {
                Icon(Icons.Default.Edit, contentDescription = "Edit", tint = PurpleGrey40)
            }

            IconButton(onClick = onDeleteUserClick) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = ICON_Red)
            }
        }
    }
}



