package com.sahsisunny.gittrackr.network

import com.sahsisunny.gittrackr.model.User
import com.sahsisunny.gittrackr.model.UserDetails
import com.sahsisunny.gittrackr.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface GitHubAPI {
    companion object {
        const val BASE_URL = Constants.GIT_API_BASE_URL
    }

    @GET("orgs/{orgName}/members?per_page=100")
    fun getUsersData(@Path("orgName") orgName: String?): ArrayList<User>

    @GET("users/{username}")
    fun getUserDetails(@Path("username") username: String): UserDetails
}