package com.sahsisunny.gittrackr.services

import com.sahsisunny.gittrackr.model.UserDetails
import com.sahsisunny.gittrackr.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAPIInterface {

    @GET("orgs/{orgName}/members?per_page=100")
    fun getUsersData(@Path("orgName") orgName: String?): Call<List<User>>

    @GET("users/{username}?per_page=100")
    fun getUserDetails(@Path("username") username: String): Call<UserDetails>

}