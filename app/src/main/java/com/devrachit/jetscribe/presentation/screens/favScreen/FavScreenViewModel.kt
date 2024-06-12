package com.devrachit.jetscribe.presentation.screens.favScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.jetscribe.domain.model.Blog
import com.devrachit.jetscribe.domain.model.SharedModel
import com.devrachit.jetscribe.room.SavedBlog
import com.devrachit.jetscribe.room.SavedBlogDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavScreenViewModel @Inject constructor(
    private val database: SavedBlogDao,
    val sharedModel: SharedModel
) :ViewModel(){
    private val _allBlog = MutableStateFlow<List<SavedBlog>>(emptyList())
    val  allBlog=_allBlog.asStateFlow()

    fun getAllSavedBlog()
    {
        viewModelScope.launch (Dispatchers.IO){
            _allBlog.value=database.getSavedBlogs()
        }
    }
}