package com.sahsisunny.gittrackr

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var submitButton: Button
    private lateinit var username: EditText
    private lateinit var orgName: EditText
    private lateinit var usernameValue: String
    private lateinit var orgNameValue: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        submitButton = findViewById(R.id.submit_button)
        username = findViewById(R.id.username)
        orgName = findViewById(R.id.org_name)

        submitButton.setOnClickListener {
            submitDetails()
        }

    }

    private fun submitDetails() {
        usernameValue = username.text.toString()
        orgNameValue = orgName.text.toString()
        Log.d("LoginActivity", "Username: $usernameValue")
        Log.d("LoginActivity", "Org Name: $orgNameValue")

    }
}