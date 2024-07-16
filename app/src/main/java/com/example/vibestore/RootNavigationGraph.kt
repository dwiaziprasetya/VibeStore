package com.example.vibestore

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.ui.navigation.graph.authNav
import com.example.vibestore.ui.navigation.model.Screen
import com.example.vibestore.ui.screen.main.MainScreen
import com.example.vibestore.ui.screen.splash.SplashScreen
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = Screen.Root.route,
        startDestination = Screen.Splash.route
    ) {
        composable(
            route = Screen.Splash.route,
            exitTransition = {
                fadeOut(tween(500))
            },
            enterTransition = {
                fadeIn(tween(500))
            },
        ) {
            SplashScreen(navController)
        }
        authNav(navController = navController)
        composable(
            route = Screen.MainNav.route,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(500)
                )
            },
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(500)
                )
            }
        ){
            MainScreen()
        }
    }
}

@Preview
@Composable
private fun RootNavigationGraphPreview() {
    VibeStoreTheme {
        RootNavigationGraph(navController = rememberNavController())
    }
}