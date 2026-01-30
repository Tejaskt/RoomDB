package com.example.roomdb.data.repository

import com.example.roomdb.data.local.dao.UserDao
import com.example.roomdb.data.local.entity.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor (
    private val userDao: UserDao
) {
    val users : Flow<List<User>> = userDao.getAllUsers()

    fun getUserById(id: Int) : Flow<User?> = userDao.getUserById(id)

    val usersCount: Flow<Int> = userDao.observeUserCount()
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