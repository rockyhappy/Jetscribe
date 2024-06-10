package com.devrachit.jetscribe.domain.repository

import androidx.paging.PagingData
import com.devrachit.jetscribe.domain.model.Blog
import kotlinx.coroutines.flow.Flow

interface JetscribeRepository {
    suspend fun getBlogs(): Flow<PagingData<Blog>>
}