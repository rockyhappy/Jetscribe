package com.devrachit.jetscribe.presentation.screens.blogScreen

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun BlogScreen(
    url: String
) {
    AndroidView(
        {
            WebView(it)
        }
    ) { webView ->
        webView.loadUrl(url)
    }
}