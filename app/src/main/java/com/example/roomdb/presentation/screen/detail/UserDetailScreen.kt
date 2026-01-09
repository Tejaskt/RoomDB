package com.example.roomdb.presentation.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.roomdb.presentation.screen.home.HomeViewModel

@Composable
fun UserDetailScreen(
    viewModel: HomeViewModel,
    userId: Int
) {
    val users by viewModel.users.collectAsState()
    val user = users.find { it.id == userId }

    user?.let {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Name: ${it.name}")
            Text("Email: ${it.email}")
            Text("Age: ${it.age}")
            Text("College: ${it.collage}")
            Text("Stream: ${it.stream}")
        }
    }
}