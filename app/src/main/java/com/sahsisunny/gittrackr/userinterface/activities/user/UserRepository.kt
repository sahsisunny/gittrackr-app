package com.sahsisunny.gittrackr.userinterface.activities.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sahsisunny.gittrackr.model.User
import com.sahsisunny.gittrackr.network.GitHubAPI
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val gitHubAPI: GitHubAPI,
) {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    suspend fun getUsersData(orgName: String) {
        val result = gitHubAPI.getUsersData(orgName)
        _users.postValue(result)
    }
}
