package com.example.roomdb.presentation.screen.addEditUser

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.roomdb.presentation.screen.addEditUser.components.FormTextField
import com.example.roomdb.presentation.utils.AppScaffold
import com.example.roomdb.presentation.utils.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditView(
    onBack: () -> Unit,
    viewModel: AddEditUserViewModel = hiltViewModel()
) {
    val state by viewModel.formState.collectAsState()
    val mode = viewModel.mode


    //Navigation after success

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            onBack()
        }
    }


    AppScaffold(
        topBarContent = {
            AppTopBar(
                title = if (mode == AddEditMode.ADD) "Add User" else "Edit User",
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
                    label = "Name",
                    value = state.name,
                    error = state.nameError,
                    onValueChange = { newValue ->
                        viewModel.onFieldChange { state ->
                            state.copy(name = newValue, nameError = null)
                        }
                    }
                )

                FormTextField(
                    label = "Email",
                    value = state.email,
                    error = state.emailError,
                    onValueChange = { newValue ->
                        viewModel.onFieldChange { state ->
                            state.copy(email = newValue, emailError = null)
                        }
                    }
                )

                FormTextField(
                    label = "Age",
                    value = state.age,
                    error = state.ageError,
                    onValueChange = { newValue ->
                        viewModel.onFieldChange { state ->
                            state.copy(age = newValue, ageError = null)
                        }
                    }
                )

                FormTextField(
                    label = "College",
                    value = state.college,
                    error = state.collegeError,
                    onValueChange = { newValue ->
                        viewModel.onFieldChange { state ->
                            state.copy(college = newValue, collegeError = null)
                        }
                    }
                )

                FormTextField(
                    label = "Stream",
                    value = state.stream,
                    error = state.streamError,
                    onValueChange = { newValue ->
                        viewModel.onFieldChange { state ->
                            state.copy(stream = newValue, streamError = null)
                        }
                    }
                )

                Button(onClick = { viewModel.submit() }) {
                    Text(if (mode == AddEditMode.ADD) "Create User" else "Update User")
                }
            }
        }
    }
}



// sample
/*
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevAddEditView(
    mode: AddEditMode = AddEditMode.ADD,
    onBack: () -> Unit = {}
) {
//    val addState by viewModel.addFormState.collectAsState()
//    val editState by viewModel.editFormState.collectAsState()


    AppScaffold(
        topBarContent = {
            AppTopBar(
                title = if (mode == AddEditMode.ADD) "Add User" else "Edit User",
                subtitle = if (mode == AddEditMode.ADD)
                    "Create a new user account"
                else
                    "Update user information",
                icon = {
                    IconButton(onClick = onBack,
                        modifier = Modifier.wrapContentWidth(align = Alignment.End)) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_arrow)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}, shape = FloatingActionButtonDefaults.largeShape) {
                Icon(painter = painterResource(R.drawable.icon_save),contentDescription = null)
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = ScreenSpace.Horizontal_Space),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {

                Spacer(modifier = Modifier.height(24.dp))

                FormTextField(
                    label = "Full Name *",
                    value = "Tejas kanazriya",
                    placeholder = "e.g., John Doe",
//                    error = "addState.nameError",
                    onValueChange = {}
                )

                FormTextField(
                    label = "Email Address *",
                    value = "Tejaskt@gmail.com",
                    placeholder = "e.g., john@example.com",
//                    error = "addState.emailError",
                    onValueChange = {}
                )

                FormTextField(
                    label = "Age *",
                    value = "24", // rename later if needed
                    placeholder = "e.g., +1 (555) 123-4567",
//                    error = "addState.ageError",
                    onValueChange = {}
                )

                FormTextField(
                    label = "College *",
                    value = "Marwadi ", // rename later if needed
                    placeholder = "e.g., University",
//                    error = "addState.ageError",
                    onValueChange = {}
                )

                FormTextField(
                    label = "Stream *",
                    value = "MCA", // rename later if needed
                    placeholder = "e.g., MCA",
//                    error = "addState.ageError",
                    onValueChange = {}
                )

            }
        }
    }
}

*/