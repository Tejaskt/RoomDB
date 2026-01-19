package com.example.roomdb.presentation.screen.remoteUsers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.repository.RemoteUserRepository
import com.example.roomdb.presentation.model.RemoteUser
import com.example.roomdb.presentation.utils.NetworkResult
import com.example.roomdb.presentation.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RemoteUsersViewModel (
    private val repository: RemoteUserRepository
) : ViewModel() {

    private val _usersState = MutableStateFlow<UiState<List<RemoteUser>>>(UiState.Loading)
    val usersState : StateFlow<UiState<List<RemoteUser>>> = _usersState

    init {
        fetchUsers()
    }

    fun fetchUsers(){
        viewModelScope.launch {
            _usersState.value = UiState.Loading

            when(val result = repository.fetchUsers()){

                is NetworkResult.Success -> {
                    _usersState.value = UiState.Success(result.data)
                }
                is NetworkResult.Error -> {
                    _usersState.value = UiState.Error(result.message)
                }
            }
        }
    }
}