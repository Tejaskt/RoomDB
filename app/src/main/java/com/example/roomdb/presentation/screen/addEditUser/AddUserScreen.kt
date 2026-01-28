package com.example.roomdb.presentation.screen.addEditUser

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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomdb.R
import com.example.roomdb.presentation.screen.dashboard.DashboardViewModel

/*
@Composable
fun AddUserScreen(
    viewModel: DashboardViewModel,
    onBack: () -> Unit
)
{

    val formState by viewModel.addFormState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                IconButton(
                    onClick = {
                        onBack()
                        viewModel.removeErrorState()
                    },
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(26.dp),
                    content = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_arrow)
                        )
                    },
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
                value = formState.name,
                onValueChange = { newValue ->
                    viewModel.updateAddForm { state ->
                        state.copy(name = newValue, nameError = null)
                    }
                },
                label = { Text("Name") },
                isError = formState.nameError != null
            )
            formState.nameError?.let { Text(text = it, color = Color.Red) }

            OutlinedTextField(
                value = formState.email,
                onValueChange = { newValue ->
                    viewModel.updateAddForm { state ->
                        state.copy(email = newValue, emailError = null)
                    }
                },
                label = { Text("Email") },
                isError = formState.emailError != null
            )
            formState.emailError?.let { Text(text = it, color = Color.Red) }

            OutlinedTextField(
                value = formState.age,
                onValueChange = { newValue ->
                    viewModel.updateAddForm { state ->
                        state.copy(age = newValue, ageError = null)
                    }
                },
                label = { Text("Age") },
                isError = formState.ageError != null
            )
            formState.ageError?.let { Text(text = it, color = Color.Red) }

            OutlinedTextField(
                value = formState.college,
                onValueChange = { newValue ->
                    viewModel.updateAddForm { state ->
                        state.copy(college = newValue, collegeError = null)
                    }
                },
                label = { Text("College") },
                isError = formState.collegeError != null
            )
            formState.collegeError?.let { Text(text = it, color = Color.Red) }

            OutlinedTextField(
                value = formState.stream,
                onValueChange = { newValue ->
                    viewModel.updateAddForm { state ->
                        state.copy(stream = newValue, streamError = null)
                    }
                },
                label = { Text("Stream") },
                isError = formState.streamError != null
            )
            formState.streamError?.let { Text(text = it, color = Color.Red) }

            Spacer(modifier = Modifier.height(12.dp))

            ElevatedButton(onClick = { viewModel.submitAddUser { onBack() } })
            {
                Text(
                    text = "Save User",
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }
}*/

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
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(26.dp)
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


        Box (

        ){
            Image(
                painter = painterResource(R.drawable.profilepic),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(120.dp)
                    .border(4.dp, Color(0xFF7C5454), CircleShape)
            )
            RadioButton(
                selected = true,
                onClick = {},
                colors = RadioButtonDefaults.colors(Color.Green.copy(alpha = 0.8f), unselectedColor = Color(0xE164DD17)),
                modifier = Modifier.align(Alignment.BottomEnd).offset(x = 8
                    .dp,y= 2.dp)
            )
        }

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