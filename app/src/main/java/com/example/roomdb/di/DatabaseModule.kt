package com.example.roomdb.di

import android.content.Context
import androidx.room.Room
import com.example.roomdb.data.local.AppDatabase
import com.example.roomdb.data.local.dao.RemoteUserDao
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
    )
        .fallbackToDestructiveMigration()
        .build()

    /* After making change in db
    *
    * use update the version and add this line which says : Delete DB on changes. it will wipe all the data if available
    *           .fallbackToDestructiveMigration()
    *
    * if you don't want to delete the existing data then crete migration object.
    *
         val MIGRATION_1_2 = object : Migration(1, 2) {
           override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("""
            CREATE TABLE IF NOT EXISTS remote_users (
                id INTEGER PRIMARY KEY NOT NULL,
                name TEXT,
                email TEXT
                )
            """)
            }
        }

        * and also add this lines in hilt database creation
            .addMigrations(MIGRATION_1_2)
    * */

    @Provides
    fun provideUserDao(db: AppDatabase) : UserDao = db.userDao()

    @Provides
    fun provideRemoteUserDao(db: AppDatabase) : RemoteUserDao = db.remoteUserDao()
}