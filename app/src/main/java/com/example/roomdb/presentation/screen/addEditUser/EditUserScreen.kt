package com.example.roomdb.presentation.screen.addEditUser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.roomdb.presentation.screen.dashboard.DashboardViewModel
import com.example.roomdb.presentation.utils.LoadingView
import com.example.roomdb.presentation.utils.UiState
/*
@Composable
fun EditUserScreen(
    viewModel: DashboardViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.selectedUserState.collectAsState()
    val formState by viewModel.editFormState.collectAsState()

    when (state) {

        is UiState.Loading -> {
            LoadingView()
        }

        is UiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = (state as UiState.Error).message,
                    color = Color.Red
                )
            }
        }

        is UiState.Success -> {

            val user = (state as UiState.Success<User>).data
            var name by remember(user.id) { mutableStateOf(user.name) }
            var email by remember(user.id) { mutableStateOf(user.email) }
            var age by remember(user.id) { mutableStateOf(user.age.toString()) }
            var college by remember(user.id) { mutableStateOf(user.college) }
            var stream by remember(user.id) { mutableStateOf(user.stream) }


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.LightGray),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                item {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                    {
                        IconButton(
                            onClick = onBack,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .size(26.dp),
                            content = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "BackArrow"
                                )
                            },
                        )

                        Text(
                            text = "Edit User",
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
                        name,
                        { name = it },
                        label = { Text("Name") },
                        isError = formState.nameError != null
                    )
                    formState.nameError?.let { Text(text = it, color = Color.Red) }

                    OutlinedTextField(
                        email,
                        { email = it },
                        label = { Text("Email") },
                        isError = formState.emailError != null
                    )
                    formState.emailError?.let { Text(text = it, color = Color.Red) }

                    OutlinedTextField(
                        age,
                        { age = it },
                        label = { Text("Age") },
                        isError = formState.ageError != null
                    )
                    formState.ageError?.let { Text(text = it, color = Color.Red) }

                    OutlinedTextField(
                        college,
                        { college = it },
                        label = { Text("College") },
                        isError = formState.collegeError != null
                    )
                    formState.collegeError?.let { Text(text = it, color = Color.Red) }

                    OutlinedTextField(
                        stream,
                        { stream = it },
                        label = { Text("Stream") },
                        isError = formState.streamError != null
                    )
                    formState.streamError?.let { Text(text = it, color = Color.Red) }

                    Spacer(modifier = Modifier.height(12.dp))

                    ElevatedButton(
                        onClick = {
                            viewModel.submitEditUser(
                                userId = user.id,
                                name = name,
                                email = email,
                                age = age,
                                college = college,
                                stream = stream,
                                onSuccess = onBack
                            )
                        }
                    ) {
                        Text(
                            text = "Update User",
                            style = MaterialTheme.typography.bodyLarge,
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                }
            }
        }
    }
}

*/
