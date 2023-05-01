package com.sahsisunny.gittrackr.userinterface.activities.userDetails

import androidx.lifecycle.ViewModel
import com.sahsisunny.gittrackr.model.UserDetails
import com.sahsisunny.gittrackr.network.GitHubAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val gitHubAPI: GitHubAPI,
    private val userDetailsRepositoryImpl: UserDetailsRepositoryImpl,
) : ViewModel() {

    suspend fun getUserDetailsByLogin(username: String): UserDetails =
        userDetailsRepositoryImpl.getUserDetailsByLogin(username)

    suspend fun insert(userDetails: UserDetails) = userDetailsRepositoryImpl.insert(userDetails)
    suspend fun update(userDetails: UserDetails) = userDetailsRepositoryImpl.update(userDetails)
    suspend fun delete(userDetails: UserDetails) = userDetailsRepositoryImpl.delete(userDetails)
}
