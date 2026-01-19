package com.example.roomdb.presentation.screen.dashboard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.data.repository.UserRepository
import com.example.roomdb.presentation.utils.UiEvent
import com.example.roomdb.presentation.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.roomdb.presentation.screen.addEditUser.UserFormState
import kotlinx.coroutines.flow.update

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    /* ---------------- EVENTS ---------------- */

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    /* ---------------- DASHBOARD STATE ---------------- */

    val usersState: StateFlow<UiState<List<User>>> =
        repository.users
            .map { UiState.Success(it) as UiState<List<User>> }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                UiState.Loading
            )

    /* ---------------- SELECTED USER ---------------- */

    private val _selectedUserId =
        savedStateHandle.getStateFlow<Int?>("selected_user_id", null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val selectedUserState: StateFlow<UiState<User>> =
        _selectedUserId
            .filterNotNull()
            .flatMapLatest { repository.getUserById(it) }
            .map { user ->
                user?.let { UiState.Success(it) }
                    ?: UiState.Error("User not found")
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, UiState.Loading)

    fun selectUser(userId: Int) {
        savedStateHandle["selected_user_id"] = userId
    }

    /* ---------------- FORM STATE ---------------- */

    private val _editFormState = MutableStateFlow(UserFormState())
    val editFormState: StateFlow<UserFormState> = _editFormState

    private val _addFormState = MutableStateFlow(UserFormState())
    val addFormState: StateFlow<UserFormState> = _addFormState


    private fun validate(state: UserFormState): UserFormState {
        return state.copy(
            nameError = if (state.name.isBlank()) "Name required" else null,
            emailError = if (!state.email.contains("@")) "Invalid email" else null,
            ageError = if (state.age.toIntOrNull() == null) "Age must be a number" else null,
            collegeError = if (state.college.isBlank()) "College required" else null,
            streamError = if (state.stream.isBlank()) "Stream required" else null
        )
    }

    fun submitAddUser(onSuccess: () -> Unit) {
        val validated = validate(_addFormState.value)

        val hasError = listOf(
            validated.nameError,
            validated.emailError,
            validated.ageError,
            validated.collegeError,
            validated.streamError
        ).any { it != null }

        if (hasError) {
            _addFormState.value = validated
            return
        }

        viewModelScope.launch {
            repository.insertUser(
                User(
                    name = validated.name,
                    email = validated.email,
                    age = validated.age.toInt(),
                    collage = validated.college,
                    stream = validated.stream
                )
            )
            _addFormState.value = UserFormState()
            onSuccess()
        }
    }

    fun updateAddForm(update: (UserFormState) -> UserFormState) {
        _addFormState.update(update)
    }

    fun submitEditUser(
        userId: Int,
        name: String,
        email: String,
        age: String,
        college: String,
        stream: String,
        onSuccess: () -> Unit
    ) {
        val validated = validate(
            UserFormState(
                name = name,
                email = email,
                age = age,
                college = college,
                stream = stream
            )
        )

        val hasError = listOf(
            validated.nameError,
            validated.emailError,
            validated.ageError,
            validated.collegeError,
            validated.streamError
        ).any { it != null }

        if (hasError) {
            _editFormState.value = validated
            return
        }

        viewModelScope.launch {
            repository.updateUser(
                User(
                    id = userId,
                    name = name,
                    email = email,
                    age = age.toInt(),
                    collage = college,
                    stream = stream
                )
            )
            _editFormState.value = UserFormState()
            onSuccess()
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repository.deleteUser(user)
            _eventFlow.emit(UiEvent.ShowUndoDelete(user))
        }
    }

    fun undoDelete(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

}
