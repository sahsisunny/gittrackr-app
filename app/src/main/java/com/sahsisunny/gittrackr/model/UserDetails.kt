package com.sahsisunny.gittrackr.model

data class UserDetails(
    val avatar_url: String,
    val html_url: String,
    val id: Int,
    val login: String,
    val url: String,
    val bio: String,
    val created_at: String,
    val followers: Int,
    val following: Int,
    val name: String,
    val repos_url: String,
)