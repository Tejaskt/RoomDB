package com.example.roomdb.data.repository

import com.example.roomdb.data.local.dao.UserDao
import com.example.roomdb.data.local.entity.User

class UserRepository(
    private val userDao: UserDao
) {
    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

    suspend fun getAllUsers() : List<User> {
        return userDao.gerAllUsers()
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
}