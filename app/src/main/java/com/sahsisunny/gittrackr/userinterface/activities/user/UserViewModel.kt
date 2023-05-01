package com.sahsisunny.gittrackr.userinterface.activities.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahsisunny.gittrackr.model.User
import com.sahsisunny.gittrackr.network.GitHubAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository

) : ViewModel() {

    val userLiveData: LiveData<List<User>>
        get() = userRepository.users

    init{
        viewModelScope.launch {
            userRepository.getUsersData("github")
        }
    }
}