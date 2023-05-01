package com.sahsisunny.gittrackr.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sahsisunny.gittrackr.database.dao.UserDao
import com.sahsisunny.gittrackr.database.dao.UserDetailsDao
import com.sahsisunny.gittrackr.model.User
import com.sahsisunny.gittrackr.model.UserDetails

@Database(
    entities = [User::class, UserDetails::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userDetailsDao(): UserDetailsDao

    companion object {
        const val DATABASE_NAME = "gittrackr-db"
    }
}