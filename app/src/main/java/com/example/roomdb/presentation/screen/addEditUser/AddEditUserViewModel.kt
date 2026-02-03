package com.example.roomdb.presentation.screen.addEditUser

import android.util.Patterns
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.data.repository.UserRepository
import com.example.roomdb.presentation.screen.addEditUser.component.AddEditMode
import com.example.roomdb.presentation.screen.addEditUser.component.AddEditUiEvent
import com.example.roomdb.presentation.screen.addEditUser.component.UserFormState
import com.example.roomdb.presentation.screen.addEditUser.component.hasError
import com.example.roomdb.presentation.screen.addEditUser.component.toUser
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
) : ViewModel()
{

    private var editingUserId : Int? = null


    /*
    /* ---------- USERID & MODE ---------- */

     private val userId: Int = savedStateHandle["userId"] ?: -1

     val mode: AddEditMode = if (userId == -1) AddEditMode.ADD else AddEditMode.EDIT

    init {
        if (mode == AddEditMode.EDIT) {
            loadUser()
        }
    }
    */

    val mode : AddEditMode
        get() = if(editingUserId == null)
            AddEditMode.ADD else AddEditMode.EDIT

    /* ---------- FORM STATE ---------- */

    private val _formState = MutableStateFlow(UserFormState())
    val formState: StateFlow<UserFormState> = _formState

    /* ---------- EVENT ---------- */

    private val _uiEvent = MutableSharedFlow<AddEditUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    /* ---------- LOAD USER ---------- */

    /*
    private fun loadUser() {
        viewModelScope.launch {
            repository.getUserById(userId)
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
    */

    /** Called once from the screen */
    fun init(user : User?){
       if(user == null) return

       editingUserId = user.id
       _formState.value = UserFormState(
           name = user.name,
           email = user.email,
           age = user.age.toString(),
           college = user.college,
           stream = user.stream

       )
    }

    /* ---------- SUBMIT USER TO DB ---------- */

    fun submit() {
        val validated = validate(_formState.value)
        if (validated.hasError()) {
            _formState.value = validated
            return
        }

        viewModelScope.launch {
            /*
            if (mode == AddEditMode.ADD) {
                repository.insertUser(validated.toUser())
            } else {
                repository.updateUser(validated.toUser(userId))
            }*/

            if(editingUserId == null){
                repository.insertUser(validated.toUser())
            }else{
                repository.updateUser(
                    validated.toUser(editingUserId!!)
                )
            }
            _uiEvent.emit(AddEditUiEvent.Success)
        }
    }

    /* ---------- VALIDATION ---------- */

    private fun validate(state: UserFormState): UserFormState =
        state.copy(
            nameError = if (state.name.isBlank()) "Name required" else null,
            emailError = when{
                state.email.isBlank() -> "Email Required"
                !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> "Invalid Email address"
                else -> null
            },
            ageError = if (state.age.toIntOrNull() == null) "Invalid age" else null,
            collegeError = if (state.college.isBlank()) "College required" else null,
            streamError = if (state.stream.isBlank()) "Stream required" else null
        )

    fun onFieldChange(update: (UserFormState) -> UserFormState) {
        _formState.update(update)
    }
}

