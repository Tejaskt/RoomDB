package com.example.roomdb.presentation.screen.addEditUser

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.roomdb.R
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.presentation.screen.addEditUser.components.FormTextField
import com.example.roomdb.presentation.screen.dashboard.DashboardViewModel
import com.example.roomdb.presentation.utils.AppScaffold
import com.example.roomdb.presentation.utils.AppTopBar
import com.example.roomdb.presentation.utils.LoadingView
import com.example.roomdb.presentation.utils.ScreenSpace
import com.example.roomdb.presentation.utils.UiState
import com.example.roomdb.ui.theme.Add_Edit_Button

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditView(
    onBack: () -> Unit,
    viewModel: AddEditUserViewModel = hiltViewModel()
) {
    val state by viewModel.formState.collectAsState()
    val mode = viewModel.mode

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
                    onValueChange = {
                        viewModel.updateForm { it.copy(name = it, nameError = null) }
                    }
                )

                // repeat for other fields...

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.submit(onBack) }
                ) {
                    Text(if (mode == AddEditMode.ADD) "Create" else "Update")
                }
            }
        }
    }
}



// sample
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