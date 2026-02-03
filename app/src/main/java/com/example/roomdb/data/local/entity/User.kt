package com.example.roomdb.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val name : String,
    val email : String,
    val age : Int,
    val college : String,
    val stream : String
) : Parcelable