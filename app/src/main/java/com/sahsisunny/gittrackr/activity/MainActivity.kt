package com.sahsisunny.gittrackr.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sahsisunny.gittrackr.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity(), CoroutineScope by CoroutineScope(Dispatchers.Main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
