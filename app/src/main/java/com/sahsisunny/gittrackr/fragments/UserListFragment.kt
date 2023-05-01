package com.sahsisunny.gittrackr.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sahsisunny.gittrackr.R
import com.sahsisunny.gittrackr.adapter.UserAdapter
import com.sahsisunny.gittrackr.model.UsersItem
import com.sahsisunny.gittrackr.services.UserAPIInterface
import com.sahsisunny.gittrackr.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserListFragment : Fragment() {
    companion object {
        const val BASE_API = Constants.BASE_API
    }

    private lateinit var rvUser: RecyclerView
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)

        rvUser = view.findViewById(R.id.user_rv)
        val orgName = arguments?.getString("orgName")
        rvUser.layoutManager = LinearLayoutManager(requireContext())
        if (orgName != null) {
            getUserData(orgName)
        }
        return view
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
                userAdapter = UserAdapter(requireContext(), responseBody)
                rvUser.adapter = userAdapter
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                Log.d("MainActivity", "onFailure: ${t.message}")
            }
        })
    }
}