package com.example.roomdb.data.repository

import com.example.roomdb.data.local.dao.UserDao
import com.example.roomdb.data.local.entity.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class UserRepository (
    private val userDao: UserDao
) {
    val users : Flow<List<User>> = userDao.getAllUsers()

    fun getUserById(id: Int) : Flow<User?> = userDao.getUserById(id)
    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
}