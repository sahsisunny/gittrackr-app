package com.sahsisunny.gittrackr.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import com.sahsisunny.gittrackr.R
import com.sahsisunny.gittrackr.database.AppDatabase
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var orgNameInput: TextInputEditText
    private lateinit var exitButton: Button
    private lateinit var loginButton: Button
    private lateinit var deleteDataButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        orgNameInput = view.findViewById(R.id.org_name_input)
        exitButton = view.findViewById(R.id.exit_button)
        loginButton = view.findViewById(R.id.login_button)
        deleteDataButton = view.findViewById(R.id.delete_button)

        loginButton.setOnClickListener {
            handleLoginButtonClick()
        }
        exitButton.setOnClickListener {
            requireActivity().finish()
        }
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
        deleteDataButton.setOnClickListener {
            lifecycleScope.launch {
                handleDeleteButtonClick()
            }
        }
        return view
    }

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

    private suspend fun handleDeleteButtonClick() {
        val database = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()
        database.userDao().deleteAll()
        database.userDetailsDao().deleteAll()
        Toast.makeText(requireContext(), "Data deleted", Toast.LENGTH_SHORT).show()
    }
}