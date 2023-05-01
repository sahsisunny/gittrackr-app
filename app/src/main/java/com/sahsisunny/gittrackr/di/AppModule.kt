package com.sahsisunny.gittrackr.di

import com.sahsisunny.gittrackr.userinterface.activities.user.UserListActivity
import dagger.Component
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DBModule::class])
interface AppModule {
    fun inject(homeActivity: UserListActivity)


}