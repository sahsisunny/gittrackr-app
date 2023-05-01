package com.sahsisunny.gittrackr.userinterface.activities.userDetails

import com.sahsisunny.gittrackr.database.dao.UserDetailsDao
import com.sahsisunny.gittrackr.model.UserDetails
import javax.inject.Inject


class UserDetailsRepositoryImpl @Inject constructor(
    private val userDetailsDao: UserDetailsDao,
) : UserDetailsRepository {
    override suspend fun getUserDetailsByLogin(username: String): UserDetails =
        userDetailsDao.getUserDetailsByLogin(username)

    override suspend fun insert(userDetails: UserDetails) = userDetailsDao.insert(userDetails)
    override suspend fun delete(userDetails: UserDetails) = userDetailsDao.delete(userDetails)
    override suspend fun update(userDetails: UserDetails) = userDetailsDao.update(userDetails)
}
