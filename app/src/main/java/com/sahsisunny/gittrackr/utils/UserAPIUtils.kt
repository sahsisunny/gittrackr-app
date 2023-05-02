package com.sahsisunny.gittrackr.utils

import com.sahsisunny.gittrackr.model.UsersItem
import com.sahsisunny.gittrackr.services.UserAPIInterface
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserAPIUtils {

    private const val BASE_API = Constants.BASE_API

    fun getUserData(orgName: String, callback: Callback<List<UsersItem>>) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPIInterface::class.java)

        val call = retrofit.getUsersData(orgName)
        call.enqueue(callback)
    }
}