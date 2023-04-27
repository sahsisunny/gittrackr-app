package com.sahsisunny.gittrackr.services

import com.sahsisunny.gittrackr.model.UserDetails
import com.sahsisunny.gittrackr.model.UsersItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAPIInterface {

    @GET("orgs/github/members")
    fun getUsersData(): Call<List<UsersItem>>

//    for user details
    @GET("users/{username}")
    fun getUserDetails(@Path("username") username: String): Call<UserDetails>
//
}