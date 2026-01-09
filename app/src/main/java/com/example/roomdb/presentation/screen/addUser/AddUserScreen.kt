package com.example.roomdb.presentation.screen.addUser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.roomdb.presentation.screen.home.HomeViewModel

@Composable
fun AddUserScreen(
    viewModel: HomeViewModel,
    onBack:() -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var college by remember { mutableStateOf("") }
    var stream by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(value = age, onValueChange = { age = it }, label = { Text("Age") })
        OutlinedTextField(value = college, onValueChange = { college = it }, label = { Text("College") })
        OutlinedTextField(value = stream, onValueChange = { stream = it }, label = { Text("Stream") })

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                viewModel.addUser(
                    name,
                    email,
                    age.toInt(),
                    college,
                    stream
                )
                onBack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save User")
        }
    }
}