package com.devrachit.jetscribe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devrachit.jetscribe.presentation.screens.blogScreen.BlogScreen
import com.devrachit.jetscribe.presentation.screens.homeScreen.HomeScreen

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen() {
                navController.navigate(Screen.BlogScreen.route.plus("/$it"))
            }
        }
        composable(
            Screen.BlogScreen.route.plus("/{url}"),
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                }
            )
        ) {
            val url = it.arguments?.getString("url")
            BlogScreen(url = url ?: "")
        }
    }
}