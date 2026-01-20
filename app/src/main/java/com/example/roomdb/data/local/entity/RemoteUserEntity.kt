package com.example.roomdb.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("remote_users")
data class RemoteUserEntity(
    @PrimaryKey val id : Int,
    val name : String,
    val username : String,
    val email : String
)
