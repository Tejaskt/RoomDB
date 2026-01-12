package com.example.roomdb.presentation.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdb.data.repository.UserRepository

class DashboardViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DashboardViewModel::class.java)){
            return DashboardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}