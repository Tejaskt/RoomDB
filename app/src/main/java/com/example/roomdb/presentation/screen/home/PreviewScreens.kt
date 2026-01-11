package com.example.roomdb.presentation.screen.home

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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
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
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 8.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = MaterialTheme.colorScheme.background,
//                    contentColor = MaterialTheme.colorScheme.primary
//                ),
//                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
//            ) {
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ){
//                    Icon(Icons.Filled.Menu,"Menu", modifier = Modifier.padding(start = 8.dp))
//                    Text(
//                        text = "Welcome",
//                        fontSize = 22.sp,
//                        fontWeight = FontWeight.Bold,
//                        fontFamily = FontFamily.Monospace,
//                        modifier = Modifier
//                            .padding(8.dp),
//                        textAlign = TextAlign.Center
//                    )
//                    Icon(Icons.Filled.AccountCircle,"Account",modifier = Modifier.padding(end = 8.dp))
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            HorizontalDivider(modifier = Modifier,2.dp, color = Color.Gray)

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
                            .clickable {}
                        ,
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        shape = RoundedCornerShape(corner = CornerSize(16.dp))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            ) {
                            Image(
                                painter = painterResource(R.drawable.profilepic),
                                contentDescription = "profile Imge",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.DarkGray, CircleShape)

                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 8.dp),
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    text = user.name,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(bottom = 2.dp)
                                )

                                Text(
                                    text = user.email,
                                    fontFamily = FontFamily.Cursive,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "User Details Screen", showBackground = true,)
@Composable
private fun PrevUserDetailScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
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

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(R.drawable.profilepic),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(120.dp)
                .border(4.dp, Color(0xFF54787c),CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(){
            Text("Tejas : 23")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(Icons.Filled.Email,"email",modifier = Modifier.padding(8.dp))
            Column() {
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.labelLarge,
                )
                Text(
                    text = "Tejas@gmail.com",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(Icons.Filled.Place,"Collage",modifier = Modifier.padding(8.dp))
            Column() {
                Text(text = "College")
                Text(text = "Marwadi University")
            }
        }

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(Icons.Filled.PlayArrow,"stream",modifier = Modifier.padding(8.dp))
            Column() {
                Text(text = "Stream")
                Text(text = "MCA")
            }
        }

        ProfileItem(Icons.Default.Person, "Name", "Tejas")

        Spacer(modifier = Modifier.height(8.dp))

        ElevatedButton(
            onClick = {}
        ) {
            Text(text = "Back To Main Screen")
        }

    }
}

@Composable
fun ProfileItem(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Center
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(32.dp))
        Column {
            Text(text = label, fontWeight = FontWeight.SemiBold)
            Text(text = value, color = Color.Gray)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        
    ) {
        Icon(Icons.Filled.Email,
            "email",
            modifier = Modifier.padding(8.dp)
        )
        Column() {
            Text(
                text = "Email",
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = "Tejas@gmail.com",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
