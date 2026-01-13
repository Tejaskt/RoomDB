package com.example.roomdb.presentation.screen.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomdb.R
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.presentation.utils.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    onAddClick: () -> Unit,
    onUserClick: (Int)-> Unit,
    onEditUserClick: (Int) -> Unit
){
    val state by viewModel.usersState.collectAsState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Filled.Add, "Add User")
            }
        },

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Welcome",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description",
                            modifier = Modifier.size(26.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Localized description",
                            modifier = Modifier.size(26.dp)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },

    ) { padding ->

        when (state) {
            is UiState.Loading -> CircularProgressIndicator()

            is UiState.Error -> Text("Something went wrong")

            is UiState.Success -> {
                val users = (state as UiState.Success<List<User>>).data
                LazyColumn(
                    modifier = Modifier.padding(padding).background(color = Color.LightGray).fillMaxHeight()) {
                    items(users) { user ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable { onUserClick(user.id) },
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            shape = RoundedCornerShape(corner = CornerSize(16.dp))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Image(
                                    painter = painterResource(R.drawable.profilepic),
                                    contentDescription = "profile Image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape)
                                        .border(2.dp, Color.DarkGray, CircleShape)
                                )

                                Column(
                                    modifier = Modifier
                                        .padding(start = 8.dp),
                                    verticalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        text = user.name,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .padding(bottom = 2.dp)
                                    )
                                    Text(
                                        text = user.email,
                                        fontFamily = FontFamily.Cursive
                                    )
                                }

                                Spacer(modifier = Modifier.weight(1f))

                                IconButton(
                                    modifier = Modifier,
                                    onClick = { onEditUserClick(user.id) }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Edit,
                                        contentDescription = "edit user"
                                    )
                                }

                                IconButton(
                                    modifier = Modifier,
                                    onClick = { viewModel.deleteUser(user) }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = "Delete"
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}
