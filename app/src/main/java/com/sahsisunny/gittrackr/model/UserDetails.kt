package com.sahsisunny.gittrackr.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userDetails")
data class UserDetails(
    @PrimaryKey val id: Int,
    @ColumnInfo val login: String,
    @ColumnInfo val name: String,
    @ColumnInfo val bio: String,
    @ColumnInfo val avatar_url: String,
    @ColumnInfo val html_url: String,
    @ColumnInfo val repos_url: String,
    @ColumnInfo val created_at: String,
    @ColumnInfo val followers: Int,
    @ColumnInfo val following: Int,

    )