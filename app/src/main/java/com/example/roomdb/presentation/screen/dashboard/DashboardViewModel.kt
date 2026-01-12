package com.example.roomdb.presentation.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.data.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DashboardViewModel (
    private val repository: UserRepository
) : ViewModel(){

    val users: StateFlow<List<User>> =
        repository.users
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun getUserById(userId : Int): StateFlow<User?> =
        repository.getUserById(userId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000),null)

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