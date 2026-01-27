package com.example.roomdb.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomdb.R
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.presentation.screen.dashboard.DashboardViewModel
import com.example.roomdb.presentation.utils.LoadingView
import com.example.roomdb.presentation.utils.ScreenSpace
import com.example.roomdb.presentation.utils.UiState

@Composable
fun UserDetailScreen(
    viewModel: DashboardViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.selectedUserState.collectAsState()

    when(state) {

        is UiState.Loading -> {
            LoadingView()
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
                        .background(color = Color.LightGray)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(ScreenSpace.Vertical_Space)
                )
                {

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

                    Column(
                        modifier = Modifier.width(intrinsicSize = IntrinsicSize.Max),
                        verticalArrangement = Arrangement.spacedBy(ScreenSpace.Vertical_Space)
                    ) {
                        ProfileItem(Icons.Filled.Email, "Email", it.email)
                        ProfileItem(Icons.Filled.LocationOn, "College", it.college)
                        ProfileItem(Icons.Filled.PlayArrow, "Stream", it.stream)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    ElevatedButton(
                        onClick = { onBack() }
                    ) {
                        Text(
                            text = "Back",
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = FontFamily.SansSerif
                        )
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
            .background(color = Color.LightGray)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(ScreenSpace.Vertical_Space)
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


        Column(
            modifier = Modifier.width(intrinsicSize = IntrinsicSize.Max),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ProfileItem(Icons.Filled.Email, "Email", "Tejaskt@gmail.com")
            ProfileItem(Icons.Filled.LocationOn, "College", "Marwadi University")
            ProfileItem(Icons.Filled.PlayArrow, "Stream", "Mca")
        }

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
fun ProfileItem(icon: ImageVector, label: String, value: String ){

        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Icon(
                    icon,
                    null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
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

/* intrinsicSizeExample
@Preview(showBackground = true)
@Composable
private fun IntrinsicSizeExample() {
    Column(
        Modifier
            .background(Color.LightGray)
            .width(intrinsicSize = IntrinsicSize.Max)

    ) {
        Text(text = "mad", Modifier
            .background(Color.Gray)
            .fillMaxWidth(), fontSize = 22.sp)
        Text(text = "skills", Modifier
            .background(Color.Gray)
            .fillMaxWidth(), fontSize = 22.sp)
        Text(text = "lay-outs", Modifier
            .background(Color.Gray)
            .fillMaxWidth(), fontSize = 22.sp)
        Text(text = "tejas kanazriya", Modifier
            .background(Color.Gray)
            .fillMaxWidth(), fontSize = 22.sp)
    }
}
*/


/*
@Preview(showBackground = true , name = "normal", showSystemUi = true)
@Preview(device = Devices.FOLDABLE, showBackground = true, name = "foldable" , showSystemUi = true)
@Preview(device = Devices.PIXEL_2, name = "pixel", showSystemUi = true)
@Preview(device = Devices.TABLET, showBackground = true, name = "tablet", showSystemUi = true)
@Composable
fun ArtistCardRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Artist image",
            modifier = Modifier
                .heightIn(max = 100.dp)
                .aspectRatio(9f / 16f)
        )
        Column {
            Text("name", modifier = Modifier.fillMaxWidth())
            Text("4.05am")
        }
    }

}
*/