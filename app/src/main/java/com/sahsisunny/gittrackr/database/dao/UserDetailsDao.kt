package com.sahsisunny.gittrackr.database.dao

import androidx.room.*
import com.sahsisunny.gittrackr.model.UserDetails

@Dao
interface UserDetailsDao {
    @Query("SELECT * FROM userDetails WHERE login = :username LIMIT 1")
    suspend fun getUserDetailsByLogin(username: String): UserDetails

    @Insert
    suspend fun insert(userDetails: UserDetails)

    @Update
    suspend fun update(userDetails: UserDetails)

    @Delete
    suspend fun delete(userDetails: UserDetails)

}