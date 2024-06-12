package com.devrachit.jetscribe.presentation.navigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object BlogScreen: Screen("blog_screen")
    object FavScreen : Screen("fav_screen")
}