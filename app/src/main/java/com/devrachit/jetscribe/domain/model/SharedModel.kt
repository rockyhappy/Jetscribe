package com.devrachit.jetscribe.domain.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedModel @Inject constructor(){

    private val _blog= MutableStateFlow<Blog?>(null)
    val blog = _blog.asStateFlow()

    fun setBlog(blog: Blog){
        _blog.value = blog
    }

    fun clearBlog(){
        _blog.value = null
    }

    fun getBlog(): Blog?{
        return _blog.value
    }
}