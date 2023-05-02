package com.sahsisunny.gittrackr.utils

import com.sahsisunny.gittrackr.model.UserDetails
import com.sahsisunny.gittrackr.services.UserAPIInterface
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserDetailsAPIUtils {

    private const val BASE_API = Constants.BASE_API

    fun getUserDetailsData(username: String, callback: Callback<UserDetails>) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPIInterface::class.java)

        val call = retrofit.getUserDetails(username)
        call.enqueue(callback)
    }
}