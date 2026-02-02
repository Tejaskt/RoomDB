package com.example.roomdb.di

import com.example.roomdb.data.repository.AuthRepository
import com.example.roomdb.data.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {


    /*
    * Provides v/s Binds
    * @Provides tells Hilt HOW to create something.
    * @Binds tells Hilt WHICH implementation to use.
    */

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    /* FirebaseAuth.getInstance().currentUser
    * This data is stored on disk, not in RAM
    * When the app restarts, Firebase restores the session automatically
    * */
     companion object {
        @Provides
        @Singleton
        fun provideFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

        @Provides
        @Singleton
        fun provideFirestore() : FirebaseFirestore = FirebaseFirestore.getInstance()

    }
}