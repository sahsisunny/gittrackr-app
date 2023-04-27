package com.sahsisunny.gittrackr.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.sahsisunny.gittrackr.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity(), CoroutineScope by CoroutineScope(Dispatchers.Main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Make the splash screen full screen
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Launch the main activity after 2 seconds
        launch {
            delay(2000L)
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Cancel the coroutine scope when the activity is destroyed to avoid memory leaks
    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}
