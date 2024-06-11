package com.devrachit.jetscribe.presentation.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.devrachit.jetscribe.domain.model.Blog
import com.devrachit.jetscribe.domain.use_case.GetBlogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getBlogsUseCase: GetBlogsUseCase
):ViewModel(){
    private val _blogs = MutableStateFlow<PagingData<Blog>>(PagingData.empty())
    val blogs = _blogs.asStateFlow()

    init {
        getBlogs()
    }

    private fun getBlogs() {
        viewModelScope.launch {
            getBlogsUseCase()
                .cachedIn(viewModelScope)
                .collect {
                    _blogs.value = it
                }
        }
    }

}