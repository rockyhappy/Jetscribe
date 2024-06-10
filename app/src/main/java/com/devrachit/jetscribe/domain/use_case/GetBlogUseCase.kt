package com.devrachit.jetscribe.domain.use_case

import com.devrachit.jetscribe.domain.repository.JetscribeRepository
import javax.inject.Inject
import androidx.paging.PagingData
import com.devrachit.jetscribe.domain.model.Blog
import kotlinx.coroutines.flow.Flow



class GetBlogsUseCase @Inject constructor(
    private val blogRepository: JetscribeRepository
) {
    suspend operator fun invoke(): Flow<PagingData<Blog>> = blogRepository.getBlogs()
}