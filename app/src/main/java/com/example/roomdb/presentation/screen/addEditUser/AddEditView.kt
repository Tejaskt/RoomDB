package com.example.roomdb.presentation.screen.addEditUser

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomdb.R
import com.example.roomdb.presentation.screen.addEditUser.components.FormTextField
import com.example.roomdb.presentation.screen.dashboard.DashboardViewModel
import com.example.roomdb.presentation.utils.AppScaffold
import com.example.roomdb.presentation.utils.AppTopBar
import com.example.roomdb.presentation.utils.ScreenSpace

@Composable
fun AddEditView(
    mode: AddEditMode,
    viewModel: DashboardViewModel,
    onBack: () -> Unit
) {
    val addState by viewModel.addFormState.collectAsState()
    val editState by viewModel.editFormState.collectAsState()

    var isActive by remember { mutableStateOf(true) }

    AppScaffold(
        topBarContent = {
            AppTopBar(
                title = if (mode == AddEditMode.ADD) "Add User" else "Edit User",
                subtitle = if (mode == AddEditMode.ADD)
                    "Create a new user account"
                else
                    "Update user information",
                icon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_arrow)
                        )
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {

                Spacer(modifier = Modifier.height(24.dp))

                FormTextField(
                    label = "Full Name *",
                    value = addState.name,
                    placeholder = "e.g., John Doe",
                    error = addState.nameError,
                    onValueChange = { newValue ->
                        viewModel.updateAddForm { state ->
                            state.copy(name = newValue, nameError = null)
                        }
                    }
                )

                FormTextField(
                    label = "Email Address *",
                    value = addState.email,
                    placeholder = "e.g., john@example.com",
                    error = addState.emailError,
                    onValueChange = { newValue ->
                        viewModel.updateAddForm { state ->
                            state.copy(email = newValue, emailError = null)
                        }
                    }
                )

                FormTextField(
                    label = "Phone Number *",
                    value = addState.age, // rename later if needed
                    placeholder = "e.g., +1 (555) 123-4567",
                    error = addState.ageError,
                    onValueChange = { newValue ->
                        viewModel.updateAddForm { state ->
                            state.copy(age = newValue, ageError = null)
                        }
                    }
                )


                Button(
                    onClick = {
                        if (mode == AddEditMode.ADD) {
                            viewModel.submitAddUser { onBack() }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_save),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (mode == AddEditMode.ADD) "Create User" else "Update User"
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}


// sample
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevAddEditView(
    mode: AddEditMode = AddEditMode.ADD,
    onBack: () -> Unit = {}
) {
//    val addState by viewModel.addFormState.collectAsState()
//    val editState by viewModel.editFormState.collectAsState()

    var isActive by remember { mutableStateOf(true) }

    AppScaffold(
        topBarContent = {
            AppTopBar(
                title = if (mode == AddEditMode.ADD) "Add User" else "Edit User",
                subtitle = if (mode == AddEditMode.ADD)
                    "Create a new user account"
                else
                    "Update user information",
                icon = {
                    IconButton(onClick = onBack) {
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
                Icon(Icons.AutoMirrored.Filled.Send,contentDescription = null)
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