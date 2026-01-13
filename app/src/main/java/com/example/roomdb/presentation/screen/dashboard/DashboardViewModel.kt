package com.example.roomdb.presentation.screen.dashboard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.data.repository.UserRepository
import com.example.roomdb.presentation.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: UserRepository,
) : ViewModel(){


    // state for Dashboard
    val usersState: StateFlow<UiState<List<User>>> =
        repository.users
            .map { UiState.Success(it) as UiState<List<User>> }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly, /*
                    here we can pass
                    Eagerly : immediate start the flow even if there is no subscriber,
                    Lazily : start after there is subscriber and then never end the flow,
                    WhileSubscribed(5_000) : start the flow when there is subscriber and end the flow after 5 sec when there is no subscriber.
                */
                initialValue = UiState.Loading
            )


    // state for edit / details screens
    private val _selectedUserId = MutableStateFlow<Int?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val selectedUserState : StateFlow<UiState<User>> =
        _selectedUserId
            .filterNotNull()
            .flatMapLatest{ userId ->
                repository.getUserById(userId)
            }
            .map { user ->
                if(user == null) UiState.Error("User not found!!")
                else UiState.Success(user)
            }
            .stateIn(
                viewModelScope, SharingStarted.Eagerly, UiState.Loading
            )

    // Events
    fun selectUser(userId: Int){
        _selectedUserId.value = userId
    }

    fun clearSelectedUser(){
        _selectedUserId.value = null
    }
    fun addUser(
        name : String,
        email : String,
        age : Int,
        collage : String,
        stream : String
    ){
        viewModelScope.launch {
            repository.insertUser(
                User(
                    name = name, email = email, age = age, collage = collage, stream = stream
                )
            )
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }
    fun deleteUser(user : User){
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }
}