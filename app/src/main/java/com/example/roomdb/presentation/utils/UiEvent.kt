package com.example.roomdb.presentation.utils

import com.example.roomdb.data.local.entity.User

sealed class UiEvent{
    data class ShowUndoDelete(val user: User) : UiEvent()
}
