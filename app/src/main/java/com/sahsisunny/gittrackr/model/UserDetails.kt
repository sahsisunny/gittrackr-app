package com.sahsisunny.gittrackr.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userDetails")
data class UserDetails(
    @PrimaryKey val id: Int,
    val avatar_url: String,
    val html_url: String,
    val login: String,
    val url: String,
    val bio: String,
    val created_at: String,
    val followers: Int,
    val following: Int,
    val name: String,
    val repos_url: String,
)