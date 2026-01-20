package com.example.roomdb.data.remote.mapper

import com.example.roomdb.data.local.entity.RemoteUserEntity
import com.example.roomdb.data.remote.dto.RemoteUserDto
import com.example.roomdb.presentation.model.RemoteUser


// convert remoteUserDto to remote user
fun RemoteUserDto.toRemoteUser() : RemoteUser {
    return RemoteUser(
        id = id,
        name = "$name (@$username)",
        email = email
    )
}

// convert api - entity
fun RemoteUserDto.toEntity() : RemoteUserEntity =
    RemoteUserEntity(
        id = id,
        name = name,
        username = username,
        email = email
    )

// entity to ui
fun RemoteUserEntity.toUi() : RemoteUser =
    RemoteUser(
        id = id,
        name = "$name (@$username)",
        email = email
    )