package com.example.roomdb.presentation.screen.dashboard


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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomdb.presentation.utils.AppScaffold
import com.example.roomdb.presentation.utils.AppTopBar
import com.example.roomdb.presentation.utils.ScreenSpace
import com.example.roomdb.ui.theme.ICON_Red
import com.example.roomdb.ui.theme.PurpleGrey40

/**
 * Reusable scaffold for all screens
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UsersScreen() {
    AppScaffold(
        topBarContent = {
            AppTopBar(
                title = "Users",
                subtitle = "4 total users"
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}, shape = FloatingActionButtonDefaults.largeShape) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues),
            contentPadding = PaddingValues(ScreenSpace.Horizontal_Space)
        ) {
            item {
                UserCard("AJ", "Alice Johnson", "alice@example.com")
                UserCard("BS", "Bob Smith", "bob@example.com")
                UserCard("CD", "Carol Davis", "carol@example.com")
                UserCard("DM", "David Miller", "david@example.com")
            }
        }
    }
}

@Composable
fun UserCard(
    initials: String,
    name: String,
    email: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Avatar
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = initials,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // User info
            Column(modifier = Modifier.weight(1f)) {
                Text(text = name, style = MaterialTheme.typography.titleMedium)
                Text(text = email, style = MaterialTheme.typography.bodySmall)
            }


            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = {}) {
                Icon(Icons.Default.Edit, contentDescription = "Edit", tint = PurpleGrey40)
            }

            IconButton(onClick = {}) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = ICON_Red)
            }
        }
    }
}



