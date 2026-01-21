package com.example.roomdb.data.remote.dto


// this class exists only to talk to the Network.
data class RemoteUserDto(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
)
