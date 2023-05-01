package com.sahsisunny.gittrackr

import android.app.Application
import com.sahsisunny.gittrackr.di.AppModule
import com.sahsisunny.gittrackr.di.DaggerAppModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    lateinit var appModule: AppModule
    override fun onCreate() {
        super.onCreate()

        appModule = DaggerAppModule.builder().build()
    }
}