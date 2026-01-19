package com.example.roomdb.presentation.screen.remoteUsers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdb.data.repository.RemoteUserRepository

class RemoteUsersViewModelFactory(
    private val repository: RemoteUserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RemoteUsersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RemoteUsersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}