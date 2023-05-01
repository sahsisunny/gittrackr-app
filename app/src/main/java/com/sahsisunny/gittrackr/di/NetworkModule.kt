package com.sahsisunny.gittrackr.di

import com.sahsisunny.gittrackr.network.GitHubAPI
import com.sahsisunny.gittrackr.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class NetworkModule {

    // Retrofit Provider Function - Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.GIT_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // GitHubAPI Provider Function - Retrofit
    @Provides
    @Singleton
    fun provideGitHubAPI(retrofit: Retrofit): GitHubAPI = retrofit.create(GitHubAPI::class.java)

}

