package com.sahsisunny.gittrackr.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sahsisunny.gittrackr.R

class HomeFragment : Fragment() {
    private lateinit var orgNameInput: EditText
    private lateinit var exitButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        orgNameInput = view.findViewById(R.id.org_name_input)
        exitButton = view.findViewById(R.id.exit_button)
        loginButton = view.findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            handleLoginButtonClick()
        }
        exitButton.setOnClickListener {
            requireActivity().finish()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().finish()
        }
        return view
    }

    // For handling the login button click
    private fun handleLoginButtonClick() {
        val orgName = orgNameInput.text.toString()
        val orgNameWithoutSpaces = orgName.replace("\\s".toRegex(), "")
        if (orgNameWithoutSpaces.isNotEmpty()) {
            findNavController().navigate(R.id.action_homeFragment_to_userListFragment,
                Bundle().apply {
                    putString("orgName", orgNameWithoutSpaces)
                })
        } else {
            orgNameInput.error = "Please enter an organization name"
        }
    }


}