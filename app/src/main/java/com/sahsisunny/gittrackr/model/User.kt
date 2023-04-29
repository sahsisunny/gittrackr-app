package com.sahsisunny.gittrackr.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: Int,
    @ColumnInfo val login: String,
    @ColumnInfo val avatar_url: String,
    @ColumnInfo val url: String,
    @ColumnInfo val orgName: String
)