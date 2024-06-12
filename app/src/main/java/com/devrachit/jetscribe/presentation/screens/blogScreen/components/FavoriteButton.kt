package com.devrachit.jetscribe.presentation.screens.blogScreen.components
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.devrachit.jetscribe.ui.theme.pink


@Composable
fun FavoriteButton(isFavorite: Boolean,modifier: Modifier, onFavoriteClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onFavoriteClick() },
        containerColor = Color.White,
        shape = CircleShape,
        modifier = modifier
            .padding(top = 46.dp, end = 16.dp, bottom=40.dp)

    ) {
        Icon(
            imageVector = if (!isFavorite) Icons.Outlined.FavoriteBorder else Icons.Filled.Favorite,
            tint = pink,
            contentDescription = null
        )
    }
}