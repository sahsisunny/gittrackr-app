package com.sahsisunny.gittrackr.screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sahsisunny.gittrackr.R
import com.sahsisunny.gittrackr.adapter.UserAdapter
import com.sahsisunny.gittrackr.model.UsersItem
import com.sahsisunny.gittrackr.services.UserAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserListActivity : AppCompatActivity() {
    private var BASE_API = "https://api.github.com/"

    private lateinit var rvUser: RecyclerView
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        rvUser = findViewById(R.id.user_rv)
        val orgName = intent.getStringExtra("orgName")
        rvUser.layoutManager = LinearLayoutManager(this)
        if (orgName != null) {
            getUserData(orgName)
        }
    }


    private fun getUserData(orgName: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

            .create(UserAPIInterface::class.java)

        val call = retrofit.getUsersData(orgName)

        call.enqueue(object : Callback<List<UsersItem>> {
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>,
            ) {
                val responseBody = response.body()!!
                userAdapter = UserAdapter(this@UserListActivity, responseBody)
                rvUser.adapter = userAdapter
            }


            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                Log.d("MainActivity", "onFailure: ${t.message}")
            }

        })
    }


}


