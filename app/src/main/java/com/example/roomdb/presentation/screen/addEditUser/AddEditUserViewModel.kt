package com.example.roomdb.presentation.screen.addEditUser

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.repository.UserRepository
import com.example.roomdb.presentation.screen.addEditUser.components.AddEditUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditUserViewModel @Inject constructor(
    private val repository: UserRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val userId: Int? = savedStateHandle["userId"]

    val mode: AddEditMode =
        if (userId == -1) AddEditMode.ADD else AddEditMode.EDIT

    private val _formState = MutableStateFlow(UserFormState())
    val formState: StateFlow<UserFormState> = _formState

    private val _uiEvent = MutableSharedFlow<AddEditUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        if (mode == AddEditMode.EDIT) {
            loadUser()
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            repository.getUserById(userId!!)
                .filterNotNull()
                .collect { user ->
                    _formState.value = UserFormState(
                        name = user.name,
                        email = user.email,
                        age = user.age.toString(),
                        college = user.college,
                        stream = user.stream
                    )
                }
        }
    }

    fun onFieldChange(update: (UserFormState) -> UserFormState) {
        _formState.update(update)
    }

    fun submit() {
        val validated = validate(_formState.value)
        if (validated.hasError()) {
            _formState.value = validated
            return
        }

        viewModelScope.launch {
            if (mode == AddEditMode.ADD) {
                repository.insertUser(validated.toUser())
            } else {
                repository.updateUser(validated.toUser(userId!!))
            }
            _uiEvent.emit(AddEditUiEvent.Success)
        }
    }

    private fun validate(state: UserFormState): UserFormState =
        state.copy(
            nameError = if (state.name.isBlank()) "Name required" else null,
            emailError = if (!state.email.contains("@")) "Invalid email" else null,
            ageError = if (state.age.toIntOrNull() == null) "Invalid age" else null,
            collegeError = if (state.college.isBlank()) "College required" else null,
            streamError = if (state.stream.isBlank()) "Stream required" else null
        )
}

