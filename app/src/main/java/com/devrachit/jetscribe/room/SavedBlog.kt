package com.devrachit.jetscribe.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "saved_blog")
data class SavedBlog(
    @PrimaryKey val id: Int,
    val title: String,
    val imageUrl: String,
    val date: String,
    val url: String
)