package com.sahsisunny.gittrackr.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sahsisunny.gittrackr.R
import com.sahsisunny.gittrackr.adapter.UserDetailsAdapter
import com.sahsisunny.gittrackr.model.UserDetails
import com.sahsisunny.gittrackr.utils.UserDetailsAPIUtils
import retrofit2.Callback
import retrofit2.Response


class UserDetailsFragment : Fragment() {

    // Declare the RecyclerView and the adapter
    private lateinit var rvUserDetails: RecyclerView
    private lateinit var userAdapter: UserDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_user_details, container, false)

        // Find the RecyclerView and set its layout manager
        rvUserDetails = view.findViewById(R.id.user_details_rv)
        rvUserDetails.layoutManager = LinearLayoutManager(requireContext())
        val loader: ImageView = view.findViewById(R.id.loader)
        loader.visibility = View.VISIBLE


        // Get the username from the arguments
        val username = arguments?.getString("userName")

        // If the username is not null, fetch the user details
        if (username != null) {
            UserDetailsAPIUtils.getUserDetailsData(username, object : Callback<UserDetails> {
                override fun onResponse(
                    call: retrofit2.Call<UserDetails>,
                    response: Response<UserDetails>,
                ) {
                    val responseBody = response.body()!!
                    userAdapter = UserDetailsAdapter(requireContext(), responseBody)
                    rvUserDetails.adapter = userAdapter
                    loader.visibility = View.GONE
                }

                override fun onFailure(call: retrofit2.Call<UserDetails>, t: Throwable) {
                    Log.e("UserDetailActivity", "Error fetching user details", t)
                }
            })
        }

        return view
    }


}