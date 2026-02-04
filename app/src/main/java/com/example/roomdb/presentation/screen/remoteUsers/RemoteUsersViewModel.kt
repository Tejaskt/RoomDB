package com.example.roomdb.presentation.screen.remoteUsers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.repository.RemoteUserRepository
import com.example.roomdb.presentation.model.RemoteUser
import com.example.roomdb.presentation.screen.remoteUsers.component.NetworkResult
import com.example.roomdb.presentation.screen.remoteUsers.component.SyncState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RemoteUsersViewModel @Inject constructor (
    private val repository: RemoteUserRepository
) : ViewModel() {

    /* ---------- COLLECT THE DATA FROM DB ---------- */

    val users: StateFlow<List<RemoteUser>> =
        repository.observeUsers()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )

    /* ---------- Sync STATE ---------- */

    private val _syncState = MutableStateFlow<SyncState>(SyncState.Idle)
    val syncState : StateFlow<SyncState> = _syncState

    init {
      sync()
    }

    /* ---------- DELETE RECORDS ---------- */

    fun deleteAll(){
        viewModelScope.launch {
            repository.clearUsers()
        }
    }

    /*
    * it will fetch the data from api and insert that data into remote_user table into db
    * */
    fun sync(){
        viewModelScope.launch {
            _syncState.value = SyncState.Syncing

            when(val result = repository.syncUsers()){
                is NetworkResult.Success -> {
                    _syncState.value = SyncState.Success
                }
                is NetworkResult.Error -> {
                    _syncState.value = SyncState.Error(result.message)
                }
            }
        }
    }
}