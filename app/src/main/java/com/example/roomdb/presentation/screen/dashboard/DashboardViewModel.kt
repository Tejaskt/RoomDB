package com.example.roomdb.presentation.screen.dashboard

import android.util.Log
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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    /* ---------- EVENTS ---------- */

    private val _eventFlow = MutableSharedFlow<UiEvent>(
        replay =  0,
        extraBufferCapacity = 1
    )
    val eventFlow = _eventFlow.asSharedFlow()

    /* ---------- USERS LIST ---------- */

    val usersState: StateFlow<UiState<List<User>>> =
        repository.users
            .map { UiState.Success(it) as UiState<List<User>> }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                UiState.Loading
            )

    val userCount: StateFlow<Int> =
        repository.usersCount
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                0
            )

    /* ---------- SELECTED USER (DETAIL SCREEN ONLY) ---------- */

    private val selectedUserId =
        savedStateHandle.getStateFlow<Int?>("selected_user_id", null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val selectedUserState: StateFlow<UiState<User>> =
        selectedUserId
            .filterNotNull()
            .flatMapLatest { repository.getUserById(it) }
            .map { user ->
                user?.let { UiState.Success(it) }
                    ?: UiState.Error("User not found")
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                UiState.Loading
            )

    fun selectUser(userId: Int) {
        savedStateHandle["selected_user_id"] = userId
    }

    /* ---------- DELETE ---------- */

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

