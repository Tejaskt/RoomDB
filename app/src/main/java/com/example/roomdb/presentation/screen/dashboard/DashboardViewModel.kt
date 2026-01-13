package com.example.roomdb.presentation.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.data.repository.UserRepository
import com.example.roomdb.presentation.utils.UiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DashboardViewModel (
    private val repository: UserRepository
) : ViewModel(){

    val usersState: StateFlow<UiState<List<User>>> =
        repository.users
            .map { UiState.Success(it) as UiState<List<User>> }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UiState.Loading
            )

    private val editUserStateMap =
        mutableMapOf<Int, StateFlow<UiState<User>>>()

    fun getEditUserState(userId: Int): StateFlow<UiState<User>> {
        return editUserStateMap.getOrPut(userId) {
            repository.getUserById(userId)
                .map { user ->
                    if (user == null) {
                        UiState.Error("User not found")
                    } else {
                        UiState.Success(user)
                    }
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.Eagerly, // IMPORTANT
                    initialValue = UiState.Loading
                )
        }
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