package com.example.roomdb.presentation.screen.addEditUser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.presentation.screen.addEditUser.component.AddEditMode
import com.example.roomdb.presentation.utils.AppScaffold
import com.example.roomdb.presentation.utils.AppTopBar
import com.example.roomdb.presentation.utils.FormTextField
import com.example.roomdb.ui.theme.App_Button

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditView(
    user: User?,
    onBack: () -> Unit,
    viewModel: AddEditUserViewModel = hiltViewModel()
) {
    val state by viewModel.formState.collectAsState()
    val mode by viewModel.mode.collectAsState()

    //Navigation to dashboard after success
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            onBack()
        }
    }

    /*------------ CHANGES --------------*/


    LaunchedEffect(user) {
        viewModel.init(user)
    }

    AppScaffold(
        topBarContent = {
            AppTopBar(
                title = if (mode == AddEditMode.ADD) "Add User" else "Edit User",
                subtitle = if (mode == AddEditMode.ADD) "Create a new user account" else state.name,
                icon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            item {

                FormTextField(
                    label = "Full Name *",
                    value = state.name,
                    error = state.nameError,
                    placeholder = "e.g., John Doe",
                    onValueChange = { newValue ->
                        viewModel.onFieldChange { state ->
                            state.copy(name = newValue, nameError = null)
                        }
                    }
                )

                FormTextField(
                    label = "Email Address *",
                    value = state.email,
                    error = state.emailError,
                    placeholder = "e.g., example@gmail.com",
                    onValueChange = { newValue ->
                        viewModel.onFieldChange { state ->
                            state.copy(email = newValue, emailError = null)
                        }
                    }
                )

                FormTextField(
                    label = "Age *",
                    value = state.age,
                    error = state.ageError,
                    placeholder = "e.g., 22",
                    onValueChange = { newValue ->
                        viewModel.onFieldChange { state ->
                            state.copy(age = newValue, ageError = null)
                        }
                    }
                )

                FormTextField(
                    label = "College *",
                    value = state.college,
                    error = state.collegeError,
                    placeholder = "e.g., Oxford University",
                    onValueChange = { newValue ->
                        viewModel.onFieldChange { state ->
                            state.copy(college = newValue, collegeError = null)
                        }
                    }
                )

                FormTextField(
                    label = "Stream *",
                    value = state.stream,
                    error = state.streamError,
                    placeholder = "e.g., Psychology",
                    onValueChange = { newValue ->
                        viewModel.onFieldChange { state ->
                            state.copy(stream = newValue, streamError = null)
                        }
                    }
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    onClick = { viewModel.submit() },
                    colors = ButtonDefaults.buttonColors(containerColor = App_Button)
                ) {
                      Row(
                          horizontalArrangement = Arrangement.SpaceAround,
                          verticalAlignment = Alignment.CenterVertically
                      ) {
                          Icon(
                              Icons.Default.Done,
                              contentDescription = null,
                              modifier = Modifier.size(ButtonDefaults.IconSize),
                              tint = Color.White
                          )

                          Spacer(Modifier.width(4.dp))

                          Text(if (mode == AddEditMode.ADD) "Create User" else "Update User")
                      }
                  }
            }
        }
    }
}
