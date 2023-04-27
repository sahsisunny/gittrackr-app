package com.sahsisunny.gittrackr.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.sahsisunny.gittrackr.R

class MainActivity : AppCompatActivity() {
    private lateinit var orgNameInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        orgNameInput = findViewById(R.id.org_name_input)

        val loginButton: Button = findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            handleLoginButtonClick()
        }
    }

    private fun handleLoginButtonClick() {
        val intent = Intent(this, UserListActivity::class.java)
        val orgName = orgNameInput.text.toString()
        val orgNameWithoutSpaces = orgName.replace("\\s".toRegex(), "")
        if (orgNameWithoutSpaces.isNotEmpty()) {
            intent.putExtra("orgName", orgNameWithoutSpaces)
            startActivity(intent)
        } else {
            orgNameInput.error = "Please enter an organization name"
        }
    }
}
