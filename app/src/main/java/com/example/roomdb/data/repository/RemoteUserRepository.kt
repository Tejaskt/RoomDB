package com.example.roomdb.data.repository

import com.example.roomdb.presentation.model.RemoteUser
import com.example.roomdb.presentation.screen.remoteUsers.component.NetworkResult
import kotlinx.coroutines.flow.Flow

interface RemoteUserRepository {

    //  suspend fun fetchUsers() : NetworkResult<List<RemoteUser>>

    /* ui observeUsers this - always from room */
    fun observeUsers() : Flow<List<RemoteUser>>

    /* Triggers Api -> DB sync */
    suspend fun syncUsers(): NetworkResult<Unit>

    suspend fun clearUsers(): NetworkResult<Unit>
}