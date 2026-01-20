package com.example.roomdb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdb.data.local.entity.RemoteUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RemoteUserDao {

    @Query("SELECT * FROM remote_users")
    fun observeUsers() : Flow<List<RemoteUserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Replace = sync-safe.
    suspend fun insertUsers(users : List<RemoteUserEntity>)

    @Query("DELETE FROM remote_users")
    suspend fun clearUsers()
}