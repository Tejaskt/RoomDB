package com.example.roomdb.data.remote.mapper

import com.example.roomdb.data.remote.dto.RemoteUserDto
import com.example.roomdb.presentation.model.RemoteUser
import kotlin.String

fun RemoteUserDto.toRemoteUser() : RemoteUser {
    return RemoteUser(
        id = id,
        name = "$name (@$username)",
        email = email
    )
}