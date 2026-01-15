package com.example.roomdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdb.data.local.dao.UserDao
import com.example.roomdb.data.local.entity.User

@Database(
    entities =[User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun userDao() : UserDao

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

   }*/

}