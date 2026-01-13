package com.example.roomdb.presentation.screen

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomdb.R
import com.example.roomdb.data.local.entity.User

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Dash Board Screen", showSystemUi = true)
@Composable
private fun PrevDashboardScreen(
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val users1 = listOf(
        User(name = "Tejas", email = "kt@gmail.com", age = 24, collage = "Marwadi", stream = "Mca"),
        User(name = "Tejas", email = "kt@gmail.com", age = 24, collage = "Marwadi", stream = "Mca"),
        User(name = "Tejas", email = "kt@gmail.com", age = 24, collage = "Marwadi", stream = "Mca"),
        User(name = "Tejas", email = "kt@gmail.com", age = 24, collage = "Marwadi", stream = "Mca"),
        User(name = "Tejas", email = "kt@gmail.com", age = 24, collage = "Marwadi", stream = "Mca"),
    )
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
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
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },


    ) { padding ->

        Column (
            modifier = Modifier
                .fillMaxSize()
        ){
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .background(color = Color.LightGray)
                    .fillMaxHeight()
            ) {
                items(users1) { user ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { /* do something */}
                        ,
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
                                    .fillMaxHeight()
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
                                    fontFamily = FontFamily.Cursive,
                                    modifier = Modifier
                                )
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            IconButton(
                                modifier = Modifier,
                                onClick = {  }
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



@Preview(name = "User Details Screen", showBackground = true)
@Composable
private fun PrevUserDetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center

            )
            Icon(
                Icons.Filled.Edit,
                "Edit Profile",
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        Image(
            painter = painterResource(R.drawable.profilepic),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(120.dp)
                .border(4.dp, Color(0xFF54787c), CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Tejas : 23",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )


        Spacer(modifier = Modifier.height(20.dp))

        ProfileItem(Icons.Filled.Email, "Email", "Tejaskt@gmail.com")
        ProfileItem(Icons.Filled.LocationOn,"College","Marwadi University")
        ProfileItem(Icons.Filled.PlayArrow,"Stream","Mca")

        Spacer(modifier = Modifier.height(8.dp))
        
        ElevatedButton(
            onClick = {}
        ) {
            Text(
                text = "Back To Main Screen",
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = FontFamily.SansSerif
            )
        }

    }
}

@Composable
fun ProfileItem(icon: ImageVector, label: String, value: String) {

    Card(
        modifier = Modifier.padding(horizontal = 12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row(
            modifier = Modifier
                .padding(start = 85.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),

            ) {
            Icon(
                icon,
                null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)
            )

            Spacer(modifier = Modifier.width(32.dp))

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelLarge,
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview(name= "Add User Screen", showBackground = true)
@Composable
private fun PrevAddUserScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                "Edit Profile",
                modifier = Modifier.padding(horizontal = 10.dp).size(26.dp)
            )

            Text(
                text = "Add User",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center

            )

        }

        Spacer(modifier = Modifier.height(22.dp))

        Image(
            painter = painterResource(R.drawable.profilepic),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(120.dp)
                .border(4.dp, Color(0xFF54787c), CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = "Tejas",
            onValueChange = {},
            label = { Text("Name") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        ElevatedButton(
            onClick = {}
        ) {
            Text(
                text = "Save User",
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}