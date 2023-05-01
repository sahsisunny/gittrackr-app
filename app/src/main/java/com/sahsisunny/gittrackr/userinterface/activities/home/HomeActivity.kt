package com.sahsisunny.gittrackr.userinterface.activities.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sahsisunny.gittrackr.R
import com.sahsisunny.gittrackr.databinding.ActivityHomeBinding
import com.sahsisunny.gittrackr.userinterface.activities.user.UserListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var orgName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            orgName = binding.orgNameInput.text.toString()
            if (orgName.isNotEmpty()) {
                Log.d("HomeActivity", "onCreate: $orgName")
                val intent = Intent(this, UserListActivity::class.java)
                intent.putExtra("orgName", orgName)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enter an organization name", Toast.LENGTH_SHORT).show()
            }
        }
        binding.exitButton.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}