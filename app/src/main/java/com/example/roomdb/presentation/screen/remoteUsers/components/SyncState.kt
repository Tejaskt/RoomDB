package com.example.roomdb.presentation.screen.remoteUsers.components

sealed class SyncState {
    object Idle: SyncState()
    object Syncing : SyncState()
    object Success: SyncState()
    data class Error (val message: String) : SyncState()
}

/* core rule for sync status
*
* Sync Status is metadata not ui state
*
* data state : comes from room, always shown
* sync state : comes from api, only inform the user
*
* not to do : wrap list in UiState.loading , hide the data(room data) while sync
*
* why separate from ui : sync != Data Rendering. , sync is transient, data must stay visible.
* */