package com.sahsisunny.gittrackr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sahsisunny.gittrackr.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE orgName = :orgName")
    suspend fun getUsers(orgName: String): List<User>

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}