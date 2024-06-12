package com.devrachit.jetscribe.presentation.screens.blogScreen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.jetscribe.domain.model.SharedModel
import com.devrachit.jetscribe.room.SavedBlog
import com.devrachit.jetscribe.room.SavedBlogDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogScreenViewModel @Inject constructor(
    val sharedModel: SharedModel,
    private val database: SavedBlogDao
) : ViewModel() {
    init {
//        println(sharedModel.getBlog())
//        viewModelScope.launch {
//            println(database.getSavedBlogs())
//        }
    }
    private val _isFavorite= MutableStateFlow(false)
    val isFavorite= _isFavorite.asStateFlow()


    fun isFavorite(){
        viewModelScope.launch {
            _isFavorite.value= database.getSavedBlog(sharedModel.getBlog()!!.id) != null
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            if (_isFavorite.value) {
                database.deleteSavedBlog(sharedModel.getBlog()!!.id)
            } else {
                database.insertSavedBlog(
                    SavedBlog(
                        id = sharedModel.getBlog()!!.id,
                        title = sharedModel.getBlog()!!.title,
                        imageUrl =sharedModel.getBlog()!!.imageUrl,
                        url = sharedModel.getBlog()!!.url,
                        date = sharedModel.getBlog()!!.date
                    )
                )
            }
            _isFavorite.value=!_isFavorite.value

        }
    }
}