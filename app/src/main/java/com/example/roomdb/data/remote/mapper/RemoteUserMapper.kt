package com.example.roomdb.data.remote.mapper

import com.example.roomdb.data.local.entity.RemoteUserEntity
import com.example.roomdb.data.remote.dto.RemoteUserDto
import com.example.roomdb.presentation.model.RemoteUser


/* definition
* A mapper isolates API and database changes from the UI by translating unstable data models into stable models.
* */

/* The mapper exists to translate between layers.
* api -> dto | dto -> entity | entity -> UI
* convert remoteUserDto to remote user
*/

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