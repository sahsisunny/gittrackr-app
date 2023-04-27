package com.sahsisunny.gittrackr.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.sahsisunny.gittrackr.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), CoroutineScope by CoroutineScope(Dispatchers.Main) {
    //    Declare the following variables for the views:
    private lateinit var orgNameInput: EditText
    private lateinit var exitButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Initialize the views by calling findViewById() and assigning them to the variables:
        orgNameInput = findViewById(R.id.org_name_input)
        exitButton = findViewById(R.id.exit_button)
        loginButton = findViewById(R.id.login_button)

//        Set an OnClickListener on the loginButton:
        loginButton.setOnClickListener {
            launch {
                handleLoginButtonClick()
            }
        }

//        Set an OnClickListener on the exitButton:
        exitButton.setOnClickListener {
            finish()
        }
    }

    //    Create a private function called handleLoginButtonClick() that takes no parameters and returns Unit:
    private fun handleLoginButtonClick() {
        val intent = Intent(this@MainActivity, UserListActivity::class.java)
        val orgName = orgNameInput.text.toString()
        val orgNameWithoutSpaces = orgName.replace("\\s".toRegex(), "")
        if (orgNameWithoutSpaces.isNotEmpty()) {
            intent.putExtra("orgName", orgNameWithoutSpaces)
            startActivity(intent)
        } else {
            orgNameInput.error = "Please enter an organization name"
        }
    }

    //    Override the onDestroy() method and call cancel() on the CoroutineScope:
    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}
