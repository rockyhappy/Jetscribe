package com.devrachit.jetscribe.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devrachit.jetscribe.domain.model.Blog


@Dao
interface SavedBlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedBlog(savedBlog: SavedBlog)

    @Query("SELECT * FROM saved_blog")
    fun getSavedBlogs(): List<SavedBlog>

    @Query("DELETE FROM saved_blog WHERE id = :id")
    suspend fun deleteSavedBlog(id: Int)

    @Query("SELECT * FROM saved_blog WHERE id = :id")
    suspend fun getSavedBlog(id: Int): SavedBlog
}