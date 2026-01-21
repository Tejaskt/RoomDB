package com.example.roomdb.di

import com.example.roomdb.data.repository.RemoteUserRepository
import com.example.roomdb.data.repository.RemoteUserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRemoteUserRepository(
        impl: RemoteUserRepositoryImpl
    ): RemoteUserRepository

}