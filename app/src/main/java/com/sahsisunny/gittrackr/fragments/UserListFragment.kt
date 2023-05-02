package com.sahsisunny.gittrackr.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sahsisunny.gittrackr.R
import com.sahsisunny.gittrackr.adapter.UserAdapter
import com.sahsisunny.gittrackr.model.UsersItem
import com.sahsisunny.gittrackr.utils.UserAPIUtils
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListFragment : Fragment() {

    private lateinit var rvUser: RecyclerView
    lateinit var userAdapter: UserAdapter
    private lateinit var loader: ImageView
    private lateinit var orgName: String
    private var responseData: List<UsersItem>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        rvUser = view.findViewById(R.id.user_rv)
        orgName = arguments?.getString("orgName").toString()
        rvUser.layoutManager = LinearLayoutManager(requireContext())
        loader = view.findViewById(R.id.loader)
        loader.visibility = View.VISIBLE
        if (responseData == null && orgName != null) {
            setUserData()
        } else {
            userAdapter = UserAdapter(requireContext(), responseData!!)
            rvUser.adapter = userAdapter
            loader.visibility = View.GONE
        }
        return view
    }

    private fun setUserData() {
        lifecycleScope.launch {
            UserAPIUtils.getUserData(
                orgName,
                object : Callback<List<UsersItem>> {
                    override fun onResponse(
                        call: Call<List<UsersItem>>,
                        response: Response<List<UsersItem>>,
                    ) {
                        responseData = response.body()!!
                        userAdapter = UserAdapter(requireContext(), responseData!!)
                        rvUser.adapter = userAdapter
                        loader.visibility = View.GONE
                    }

                    override fun onFailure(
                        call: Call<List<UsersItem>>,
                        t: Throwable,
                    ) {
                        Log.d("MainActivity", "onFailure: ${t.message}")
                    }
                })
        }
    }
}
