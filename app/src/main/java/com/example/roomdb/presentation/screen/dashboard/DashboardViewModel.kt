package com.example.roomdb.presentation.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel (
    private val repository: UserRepository
) : ViewModel(){

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users : StateFlow<List<User>> = _users

    fun loadUsers(){
        viewModelScope.launch {
            _users.value = repository.getAllUsers()
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
            loadUsers()
        }
    }

    fun deleteUser(user : User){
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }
}