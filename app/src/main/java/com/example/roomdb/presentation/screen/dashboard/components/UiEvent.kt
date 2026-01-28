package com.example.roomdb.presentation.screen.dashboard.components

import com.example.roomdb.data.local.entity.User

sealed class UiEvent{
    data class ShowUndoDelete(val user: User) : UiEvent()
}