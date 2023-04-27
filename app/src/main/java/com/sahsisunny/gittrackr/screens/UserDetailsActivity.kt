package com.sahsisunny.gittrackr.screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.sahsisunny.gittrackr.R
import com.sahsisunny.gittrackr.adapter.UserDetailsAdapter
import com.sahsisunny.gittrackr.model.UserDetails
import com.sahsisunny.gittrackr.services.UserAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDetailsActivity : AppCompatActivity() {
    private var BASE_API = "https://api.github.com/"

    private lateinit var rvUserDetails: RecyclerView
    private lateinit var userAdapter: UserDetailsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        rvUserDetails = findViewById(R.id.user_details_rv)
        rvUserDetails.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        val username = intent.getStringExtra("login")
        if (username != null) {
            getUserData(username)
        }
    }

    private fun getUserData(username: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPIInterface::class.java)

        val call = retrofit.getUserDetails(username)

        call.enqueue(object : Callback<UserDetails> {
            override fun onResponse(
                call: Call<UserDetails>,
                response: Response<UserDetails>,
            ) {
                val responseBody = response.body()!!
                userAdapter = UserDetailsAdapter(this@UserDetailsActivity, responseBody)
                rvUserDetails.adapter = userAdapter
            }

            override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                Log.e("UserDetailActivity", "Error fetching user details", t)
            }
        })
    }
}
