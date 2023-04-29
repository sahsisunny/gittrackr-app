package com.sahsisunny.gittrackr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sahsisunny.gittrackr.model.UserDetails

@Dao
interface UserDetailsDao {
    @Query("SELECT * FROM userDetails WHERE login = :username")
    suspend fun getUserDetails(username: String): UserDetails

    @Insert
    suspend fun insertUserDetails(userDetails: UserDetails)

    @Update
    suspend fun updateUserDetails(userDetails: UserDetails)

    @Delete
    suspend fun deleteUserDetails(userDetails: UserDetails)

    @Query("DELETE FROM userDetails")
    suspend fun deleteAllUserDetails()
}