package com.devrachit.jetscribe.data.remote

import com.devrachit.jetscribe.data.remote.dto.BlogDto
import retrofit2.http.GET
import retrofit2.http.Query

interface JetscribeApi {
    @GET("wp-json/wp/v2/posts")
    suspend fun getBlogs(@Query("page") page: Int, @Query("per_page") pageSize: Int): List<BlogDto>
}