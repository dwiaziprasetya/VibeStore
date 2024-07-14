package com.example.vibestore.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vibestore.ui.navigation.Screen
import com.example.vibestore.ui.screen.home.HomeScreen
import com.example.vibestore.ui.screen.profile.ProfileScreen

@Composable
fun HomeNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Screen.MainNav.route,
        startDestination = Screen.Home.route,
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }
    }
}