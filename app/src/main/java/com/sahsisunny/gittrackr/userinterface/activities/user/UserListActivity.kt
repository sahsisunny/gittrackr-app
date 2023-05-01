package com.sahsisunny.gittrackr.userinterface.activities.user

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sahsisunny.gittrackr.App
import com.sahsisunny.gittrackr.R
import com.sahsisunny.gittrackr.databinding.ActivityUserListBinding
import com.sahsisunny.gittrackr.adapter.UserAdapter
import javax.inject.Inject

//@AndroidEntryPoint
//class UserListActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityUserListBinding
//    private lateinit var orgName: String
//    private val viewModel by viewModels<UserViewModel>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityUserListBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        orgName = intent.getStringExtra("orgName").toString()
//        Log.d("UserListActivity", "onCreate: $orgName")
//
//        setUserListDataToDB()
//    }
//
//    private fun setUserListDataToDB() {
//        Log.d("UserListActivity", "setUserListDataToDB called")
//        CoroutineScope(Dispatchers.Main).launch {
//            val userList = viewModel.getUsersByOrg(orgName)
//            if (userList.isEmpty()) {
//                viewModel.users.observe(this@UserListActivity) {
//                    CoroutineScope(Dispatchers.Main).launch {
//                        for (i in viewModel.users.value!!) {
//                            viewModel.insert(i)
//                            Log.d("UserListActivity", "xPro: $i")
//                        }
//                    }.invokeOnCompletion { setUserData() }
//                }
//            } else {
//                setUserData()
//                CoroutineScope(Dispatchers.Main).launch {
//                    for (i in viewModel.users.value!!) {
//                        viewModel.update(i)
//                    }
//                }
//            }
//        }
//    }
//
//    // This function is for setting the data to the recycler view
//    private fun setUserData() {
//        Log.d("UserListActivity", "setUserData called")
//        CoroutineScope(Dispatchers.Main).launch {
//            val user = viewModel.getUsersByOrg(orgName)
//            val adapter = UserAdapter(this@UserListActivity)
//            binding.userRv.adapter = adapter
//            adapter.submitList(user)
//        }
//    }
//
//    private fun openUserDetails(login: String) {
//        val intent = Intent(this, UserDetailsActivity::class.java)
//        intent.putExtra("login", login)
//        startActivity(intent)
//    }
//}


class UserListActivity : AppCompatActivity(){
    lateinit var userViewModel: UserViewModel
    private lateinit var binding: ActivityUserListBinding
    private lateinit var orgName: String
    private val viewModel by viewModels<UserViewModel>()
    @Inject
    lateinit var userViewModelFactory: UserViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        (application as App).appModule.inject(this@UserListActivity)

        userViewModel = ViewModelProvider(this, userViewModelFactory).get(UserViewModel::class.java)

        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        orgName = intent.getStringExtra("orgName").toString()
        Log.d("UserListActivity", "onCreate: $orgName")

        userViewModel.userLiveData.observe(this, Observer {
            val adapter = UserAdapter(this)
            binding.userRv.adapter = adapter
            adapter.submitList(it)
        })
        }


    }
