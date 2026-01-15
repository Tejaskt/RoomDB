package com.example.roomdb.data.remote.api

import retrofit2.http.GET

interface UserApi{
    @GET("users")
    suspend fun getUsers(): List<RemoteUserDto>
}