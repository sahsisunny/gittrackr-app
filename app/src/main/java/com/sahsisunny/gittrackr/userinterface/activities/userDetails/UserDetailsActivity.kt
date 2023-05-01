package com.sahsisunny.gittrackr.userinterface.activities.userDetails

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sahsisunny.gittrackr.databinding.ActivityUserDetailsBinding
import com.sahsisunny.gittrackr.adapter.UserDetailsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding
    private lateinit var login: String
    private val viewModel by viewModels<UserDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        login = intent.getStringExtra("login").toString()
        setUserDetailsDataToDB()
    }

    private fun setUserDetailsDataToDB() {
        CoroutineScope(Dispatchers.Main).launch {
            val userDetails = viewModel.getUserDetailsByLogin(login)
            if (userDetails == null) {
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.insert(userDetails)
                }.invokeOnCompletion { setUserDetailsData() }
            } else {
                setUserDetailsData()
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.update(userDetails)
                }
            }
        }
    }

    private fun setUserDetailsData() {
        CoroutineScope(Dispatchers.Main).launch {
            val userDetails = viewModel.getUserDetailsByLogin(login)
            val adapter = UserDetailsAdapter(this@UserDetailsActivity)
            binding.userDetailsRv.adapter = adapter
//            adapter.submitList(userDetails)
        }
    }

    private fun openUserDetails(login: String) {
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra("login", login)
        startActivity(intent)
    }
}
