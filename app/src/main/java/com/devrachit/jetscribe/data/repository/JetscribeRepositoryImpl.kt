package com.devrachit.jetscribe.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.devrachit.jetscribe.data.remote.JetscribeApi
import com.devrachit.jetscribe.data.remote.JetscribePagingSource
import com.devrachit.jetscribe.domain.repository.JetscribeRepository
import javax.inject.Inject

class JetscribeRepositoryImpl @Inject constructor(
    private val jetscribeApi: JetscribeApi
) : JetscribeRepository {
    override suspend fun getBlogs() = Pager(
        config = PagingConfig(
            pageSize = 10
        ),
        pagingSourceFactory = {
            JetscribePagingSource(jetscribeApi)
        }
    ).flow
}