package com.devrachit.jetscribe.presentation.screens.blogScreen

import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.devrachit.jetscribe.presentation.screens.blogScreen.components.FavoriteButton

@Composable
fun BlogScreen(
    navController: NavController
) {

    val viewModel: BlogScreenViewModel = hiltViewModel()
    val url = viewModel.sharedModel.getBlog()!!.url
    val isFavorite = viewModel.isFavorite.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.isFavorite()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd,
    ) {
        AndroidView(
            {
                WebView(it)

            }
        ) { webView ->
            webView.loadUrl(url)

        }
        FavoriteButton(
            isFavorite = isFavorite.value,
            modifier = Modifier,
            onFavoriteClick = {  viewModel.toggleFavorite() })
    }
}