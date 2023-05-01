package com.sahsisunny.gittrackr.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "userDetails")
data class UserDetails(
    @PrimaryKey val id: Int,
    @ColumnInfo val login: String,
    @ColumnInfo val name: String?,
    @ColumnInfo val bio: String?,
    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatar_url") val avatarUrl: String?,
    @SerializedName("html_url")
    @ColumnInfo(name = "html_url") val htmlUrl: String,
    @ColumnInfo val repos_url: String,
    @ColumnInfo val created_at: String,
    @ColumnInfo val followers: Int,
    @ColumnInfo val following: Int,

    )