package com.example.roomdb.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdb.data.local.dao.RemoteUserDao
import com.example.roomdb.data.local.dao.UserDao
import com.example.roomdb.data.local.entity.User

@Database(
    entities =[User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){

    // local user Dao
    abstract fun userDao() : UserDao

    // dao for remote user database
    abstract fun remoteUserDao() : RemoteUserDao

    /* removed manual creation because of hilt.*/

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

   }

}