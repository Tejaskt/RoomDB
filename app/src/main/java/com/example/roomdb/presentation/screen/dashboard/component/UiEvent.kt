package com.example.roomdb.presentation.screen.dashboard.component

import com.example.roomdb.data.local.entity.User

sealed class UiEvent{
    data class ShowUndoDelete(val user: User) : UiEvent()
}