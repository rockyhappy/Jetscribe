package com.devrachit.jetscribe.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devrachit.jetscribe.presentation.screens.blogScreen.BlogScreen
import com.devrachit.jetscribe.presentation.screens.favScreen.FavScreen
import com.devrachit.jetscribe.presentation.screens.homeScreen.HomeScreen

@ExperimentalMaterial3Api
@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(
            Screen.BlogScreen.route) {
            BlogScreen(navController)
        }
        composable(Screen.FavScreen.route)
        {
            FavScreen(navController)
        }
    }
}