package com.devrachit.jetscribe.presentation.screens.favScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.devrachit.jetscribe.domain.model.Blog
import com.devrachit.jetscribe.presentation.navigation.Screen
import com.devrachit.jetscribe.presentation.screens.homeScreen.components.BlogItem
import com.devrachit.jetscribe.ui.theme.GrayShade1
import com.devrachit.jetscribe.ui.theme.GrayShade2
import com.devrachit.jetscribe.ui.theme.GrayShade4

@ExperimentalMaterial3Api
@Composable
fun FavScreen(navController: NavController)
{
    val viewModel: FavScreenViewModel= hiltViewModel()
    val allBlogs= viewModel.allBlog.collectAsStateWithLifecycle().value
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val onItemClick2:(Blog) -> Unit = { blog ->
        viewModel.sharedModel.setBlog(blog)
        navController.navigate(Screen.BlogScreen.route)
    }

    LaunchedEffect(true) {
        viewModel.getAllSavedBlog()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Favorites",
                        color = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    )
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = GrayShade2)
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(GrayShade4)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
        ,
        containerColor = GrayShade4
        )
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding( end = 16.dp, start = 16.dp, bottom = 30.dp)
                .background(GrayShade4)
                .padding(it),
            contentPadding = PaddingValues(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(allBlogs.size) {
                val item = Blog(
                    id = allBlogs[it].id,
                    date = allBlogs[it].date,
                    url = allBlogs[it].url,
                    imageUrl = allBlogs[it].imageUrl,
                    title = allBlogs[it].title
                )
                allBlogs[it]?.let { blog ->
                    BlogItem(
                        item
                    ) {
                        onItemClick2(item)
                    }
                }

            }
        }
    }
}