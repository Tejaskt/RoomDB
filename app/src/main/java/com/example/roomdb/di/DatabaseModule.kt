package com.example.roomdb.di

import android.content.Context
import androidx.room.Room
import com.example.roomdb.data.local.AppDatabase
import com.example.roomdb.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "users"
    ).build()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase) : UserDao = db.userDao()
}