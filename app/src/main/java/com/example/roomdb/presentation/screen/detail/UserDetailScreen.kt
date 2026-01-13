package com.example.roomdb.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.roomdb.R
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.presentation.screen.ProfileItem
import com.example.roomdb.presentation.screen.dashboard.DashboardViewModel
import com.example.roomdb.presentation.utils.UiState

@Composable
fun UserDetailScreen(
    viewModel: DashboardViewModel,
    userId: Int,
    onBack: () -> Unit
) {
    val state by viewModel.getEditUserState(userId).collectAsState()

    when(state) {

        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        is UiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = (state as UiState.Error).message,
                    color = Color.Red
                )
            }
        }
        is UiState.Success ->{

            val user = (state as UiState.Success<User>).data
            user.let {

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
                        "${it.name} : ${it.age}",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    ProfileItem(Icons.Filled.Email, "Email", it.email)
                    ProfileItem(Icons.Filled.LocationOn,"College",it.collage)
                    ProfileItem(Icons.Filled.PlayArrow,"Stream",it.stream)

                    Spacer(modifier = Modifier.height(8.dp))

                    ElevatedButton(
                        onClick = { onBack() }
                    ) {
                        Text(
                            text = "Back To Main Screen",
                            style = MaterialTheme.typography.bodyLarge,
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                }
            }
        }
    }

}