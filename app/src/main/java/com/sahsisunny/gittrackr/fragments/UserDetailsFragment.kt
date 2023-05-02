package com.sahsisunny.gittrackr.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sahsisunny.gittrackr.R
import com.sahsisunny.gittrackr.adapter.UserDetailsAdapter
import com.sahsisunny.gittrackr.database.AppDatabase
import com.sahsisunny.gittrackr.model.UserDetails
import com.sahsisunny.gittrackr.utils.UserDetailsAPIUtils
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("NAME_SHADOWING")
class UserDetailsFragment : Fragment() {

    private lateinit var rvUserDetails: RecyclerView
    private lateinit var userAdapter: UserDetailsAdapter
    private lateinit var database: AppDatabase
    private lateinit var loader: ImageView
    lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_user_details, container, false)
        rvUserDetails = view.findViewById(R.id.user_details_rv)
        rvUserDetails.layoutManager = LinearLayoutManager(requireContext())
        loader = view.findViewById(R.id.loader)
        loader.visibility = View.VISIBLE

        // Get the username from the arguments
        username = arguments?.getString("userName").toString()

        // If the username is not null, fetch the user details
        if (username != null) {
            setData()
        }
        return view
    }

    private fun setData() {
        lifecycleScope.launch {
            database = AppDatabase.getInstance(requireContext())
            val userDetails = database.userDetailsDao().getUserDetailsByLogin(username)
            if (userDetails != null) {
                userAdapter = UserDetailsAdapter(requireContext(), userDetails)
                Toast.makeText(requireContext(), "Data from database", Toast.LENGTH_SHORT).show()
                rvUserDetails.adapter = userAdapter
                loader.visibility = View.GONE
            } else
                UserDetailsAPIUtils.getUserDetailsData(
                    username,
                    object : Callback<UserDetails> {
                        override fun onResponse(
                            call: Call<UserDetails>,
                            response: Response<UserDetails>,
                        ) {
                            val responseBody = response.body()!!
                            lifecycleScope.launch {
                                database = AppDatabase.getInstance(requireContext())
                                val userDetails =
                                    database.userDetailsDao().getUserDetailsByLogin(username)
                                if (userDetails != null) {
                                    database.userDetailsDao().update(responseBody)
                                } else {
                                    database.userDetailsDao().insert(responseBody)
                                }
                            }
                            userAdapter = UserDetailsAdapter(requireContext(), responseBody)
                            Toast.makeText(
                                requireContext(),
                                "Data from API",
                                Toast.LENGTH_SHORT
                            ).show()
                            rvUserDetails.adapter = userAdapter
                            loader.visibility = View.GONE
                        }

                        override fun onFailure(
                            call: Call<UserDetails>,
                            t: Throwable,
                        ) {
                            Log.e("UserDetailActivity", "Error fetching user details", t)
                        }
                    })
        }
    }
}