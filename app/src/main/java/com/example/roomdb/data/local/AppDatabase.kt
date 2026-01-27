package com.example.roomdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdb.data.local.dao.RemoteUserDao
import com.example.roomdb.data.local.dao.UserDao
import com.example.roomdb.data.local.entity.RemoteUserEntity
import com.example.roomdb.data.local.entity.User

@Database(
    entities = [User::class, RemoteUserEntity::class],
    version = 3
)
abstract class AppDatabase : RoomDatabase(){

    // local user Dao
    abstract fun userDao() : UserDao

    // dao for remote user database
    abstract fun remoteUserDao() : RemoteUserDao

    /* removed manual creation because of hilt.

   companion object{
       @Volatile
       private var INSTANCE: AppDatabase? = null



       fun getDatabase(context: Context): AppDatabase{
           return INSTANCE ?: synchronized(this){
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   AppDatabase::class.java,
                   "users"
               ).build()
               INSTANCE = instance
               instance
           }
       }

   } */

}