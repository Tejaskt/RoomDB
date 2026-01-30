package com.example.roomdb.di

import com.example.roomdb.data.local.dao.UserDao
import com.example.roomdb.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        dao: UserDao
    ) : UserRepository = UserRepository(dao)

//    @Binds
//    @Singleton
//    abstract fun bindUserRepository(
//        impl: UserRepository
//    ): UserRepository // we can only map interface to its implementation.


}