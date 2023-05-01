package com.sahsisunny.gittrackr.userinterface.activities.userDetails

import com.sahsisunny.gittrackr.model.UserDetails

interface UserDetailsRepository {
    suspend fun getUserDetailsByLogin(username: String): UserDetails

    suspend fun insert(userDetails: UserDetails)

    suspend fun update(userDetails: UserDetails)

    suspend fun delete(userDetails: UserDetails)

}