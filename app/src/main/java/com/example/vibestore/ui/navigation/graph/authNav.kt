package com.example.vibestore.ui.navigation.graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.vibestore.ui.navigation.Screen
import com.example.vibestore.ui.screen.registration.login.LoginScreen
import com.example.vibestore.ui.screen.registration.signup.SignUpScreen
import com.example.vibestore.ui.screen.registration.welcome.WelcomeScreen

fun NavGraphBuilder.authNav(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Welcome.route,
        route = Screen.AuthNav.route
    ) {
        composable(
            route = Screen.Welcome.route,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(500)
                )
            }
        ) {
            WelcomeScreen(navController)
        }
        composable(
            route = Screen.Login.route,
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
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.SignUp.route,
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
            SignUpScreen(navController = navController)
        }
    }
}