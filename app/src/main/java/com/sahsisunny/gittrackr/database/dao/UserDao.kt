package com.sahsisunny.gittrackr.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sahsisunny.gittrackr.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE orgName = :orgName")
    suspend fun getUsersByOrg(orgName: String): List<User>

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()


}