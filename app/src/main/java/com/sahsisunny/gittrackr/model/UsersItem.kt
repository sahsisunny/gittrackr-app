package com.sahsisunny.gittrackr.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UsersItem(
    @PrimaryKey val id: Int,
    val avatar_url: String,
    val html_url: String,
    val login: String,
    val url: String,
    val orgName: String,
)