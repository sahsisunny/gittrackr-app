package com.sahsisunny.gittrackr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sahsisunny.gittrackr.model.UsersItem

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE orgName = :orgName")
    suspend fun getUsers(orgName: String): List<UsersItem>

    @Insert
    suspend fun insert(user: UsersItem)

    @Update
    suspend fun update(user: UsersItem)

    @Delete
    suspend fun delete(user: UsersItem)
}