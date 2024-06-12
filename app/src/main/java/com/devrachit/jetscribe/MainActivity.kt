package com.devrachit.jetscribe

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowInsetsControllerCompat
import com.devrachit.jetscribe.presentation.navigation.App
import com.devrachit.jetscribe.ui.theme.GrayShade2
import com.devrachit.jetscribe.ui.theme.GrayShade4
import com.devrachit.jetscribe.ui.theme.JetscribeTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.statusBarColor= GrayShade2.toArgb()
        window.navigationBarColor = GrayShade2.toArgb()

        setContent {
            val activity = LocalContext.current as? Activity
            activity?.window?.let { window ->
                WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
                    false
            }
            JetscribeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

