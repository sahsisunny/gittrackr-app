package com.sahsisunny.gittrackr.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UsersItem(
    @PrimaryKey val id: Int,
    @ColumnInfo val avatar_url: String,
    @ColumnInfo val html_url: String,
    @ColumnInfo val login: String,
    @ColumnInfo val url: String,
    @ColumnInfo var orgName: String,
)