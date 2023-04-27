package com.sahsisunny.gittrackr.screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.sahsisunny.gittrackr.R
import com.sahsisunny.gittrackr.adapter.UserDetailsAdapter
import com.sahsisunny.gittrackr.model.UserDetails
import com.sahsisunny.gittrackr.utils.UserDetailsAPIUtils
import retrofit2.Callback
import retrofit2.Response

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var rvUserDetails: RecyclerView
    private lateinit var userAdapter: UserDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        rvUserDetails = findViewById(R.id.user_details_rv)
        rvUserDetails.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        val username = intent.getStringExtra("login")

        if (username != null) {
            UserDetailsAPIUtils.getUserDetailsData(username, object : Callback<UserDetails> {
                override fun onResponse(
                    call: retrofit2.Call<UserDetails>,
                    response: Response<UserDetails>,
                ) {
                    val responseBody = response.body()!!
                    userAdapter = UserDetailsAdapter(this@UserDetailsActivity, responseBody)
                    rvUserDetails.adapter = userAdapter
                }

                override fun onFailure(call: retrofit2.Call<UserDetails>, t: Throwable) {
                    Log.e("UserDetailActivity", "Error fetching user details", t)
                }
            })
        }
    }
}
