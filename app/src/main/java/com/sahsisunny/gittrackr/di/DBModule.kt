package com.sahsisunny.gittrackr.di

import android.content.Context
import androidx.room.Room
import com.sahsisunny.gittrackr.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class DBModule {

    // AppDatabase Provider Function - Room
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).build()

    // DAO Provider Functions - UserDao
    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

    // DAO Provider Functions - UserDetailsDao
    @Provides
    @Singleton
    fun provideUserDetailsDao(appDatabase: AppDatabase) = appDatabase.userDetailsDao()
}