package com.example.roomdb.data.remote.api

import com.example.roomdb.data.remote.dto.RemoteUserDto
import retrofit2.http.GET

interface UserApi{

    @GET("users")
    suspend fun getUsers(): List<RemoteUserDto>
}
