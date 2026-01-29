package com.example.roomdb.presentation.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.local.entity.User
import com.example.roomdb.data.repository.UserRepository
import com.example.roomdb.presentation.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    repository: UserRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    /* ---------- USER ID ---------- */

    /*
     * savedStateHandle
     * A small, lifecycle-safe key–value store owned by the ViewModel
     * */
    private val userId: Int =
        savedStateHandle["userId"]
            ?: error("userId missing for UserDetailViewModel")

    val userState: StateFlow<UiState<User>> =
        repository.getUserById(userId)
            .map { user ->
                user?.let { UiState.Success(it) }
                    ?: UiState.Error("User not found")
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                UiState.Loading
            )
}
