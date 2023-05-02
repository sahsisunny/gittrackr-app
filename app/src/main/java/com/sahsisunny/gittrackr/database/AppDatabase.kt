package com.sahsisunny.gittrackr.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sahsisunny.gittrackr.database.dao.UserDao
import com.sahsisunny.gittrackr.database.dao.UserDetailsDao
import com.sahsisunny.gittrackr.model.UserDetails
import com.sahsisunny.gittrackr.model.UsersItem

@Database(
    entities = [UsersItem::class, UserDetails::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userDetailsDao(): UserDetailsDao

    companion object {
        const val DATABASE_NAME = "gittrackr-db"

        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {

                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}