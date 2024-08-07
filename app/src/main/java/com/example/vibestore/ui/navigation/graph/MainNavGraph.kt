package com.example.vibestore.ui.navigation.graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.vibestore.ui.navigation.model.Screen
import com.example.vibestore.ui.screen.categories.CategoriesScreen
import com.example.vibestore.ui.screen.coupon.CouponScreen
import com.example.vibestore.ui.screen.detail.DetailScreen
import com.example.vibestore.ui.screen.favourite.FavouriteScreen
import com.example.vibestore.ui.screen.home.HomeScreen
import com.example.vibestore.ui.screen.mycart.MyCartScreen
import com.example.vibestore.ui.screen.notification.NotificationScreen
import com.example.vibestore.ui.screen.ourproduct.OurProductScreen
import com.example.vibestore.ui.screen.profile.ProfileScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        modifier = Modifier.padding(paddingValues),
        route = Screen.MainNav.route,
        startDestination = Screen.Home.route,
    ) {
        authNav(navController)
        composable(
            route = Screen.Home.route,
        ) {
            HomeScreen(navController)
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(
            route = Screen.DetailProduct.route,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                }
            ),
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
        ){ backStackEntry ->
            val id = backStackEntry.arguments?.getInt("productId") ?: -2
            DetailScreen(
                productId = id,
                navController
            )
        }
        composable(
            route = Screen.MyCart.route,
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
            MyCartScreen(navController)
        }
        composable(
            route = Screen.Notification.route,
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
            NotificationScreen()
        }
        composable(Screen.Coupon.route){ CouponScreen() }
        composable(Screen.Favourite.route){ FavouriteScreen() }
        composable(Screen.OurProduct.route){ OurProductScreen(navController) }
        composable(
            route = Screen.Categories.route,
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
            CategoriesScreen(navController)
        }
    }
}