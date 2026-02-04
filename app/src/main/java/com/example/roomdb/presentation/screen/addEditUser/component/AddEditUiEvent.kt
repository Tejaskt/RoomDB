package com.example.roomdb.presentation.screen.addEditUser.component

/*-- Event for navigating back to Dashboard when ADD or EDIT action perform --*/
sealed class AddEditUiEvent {
    object Success : AddEditUiEvent()
}
