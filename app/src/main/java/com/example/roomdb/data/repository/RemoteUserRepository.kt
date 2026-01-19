package com.example.roomdb.data.repository

import com.example.roomdb.presentation.model.RemoteUser
import com.example.roomdb.presentation.utils.NetworkResult

interface RemoteUserRepository {
    suspend fun fetchUsers() : NetworkResult<List<RemoteUser>>
}