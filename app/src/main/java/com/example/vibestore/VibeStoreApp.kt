package com.example.vibestore

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vibestore.ui.component.BottomNavigation
import com.example.vibestore.ui.navigation.Screen
import com.example.vibestore.ui.screen.categories.CategoriesScreen
import com.example.vibestore.ui.screen.coupon.CouponScreen
import com.example.vibestore.ui.screen.detail.DetailScreen
import com.example.vibestore.ui.screen.favourite.FavouriteScreen
import com.example.vibestore.ui.screen.home.HomeScreen
import com.example.vibestore.ui.screen.mycart.MyCartScreen
import com.example.vibestore.ui.screen.ourproduct.OurProductScreen
import com.example.vibestore.ui.screen.profile.ProfileScreen
import com.example.vibestore.ui.screen.registration.login.LoginScreen
import com.example.vibestore.ui.screen.registration.signup.SignUpScreen
import com.example.vibestore.ui.screen.registration.welcome.WelcomeScreen
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun VibeStoreApp(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute !in listOf(
                    Screen.Categories.route,
                    Screen.DetailProduct.route,
                    Screen.MyCart.route,
                    Screen.Welcome.route,
                    Screen.Login.route,
                    Screen.SignUp.route,
            )
                ){
                BottomNavigation(
                    navController = navController,
                    modifier = Modifier
                        .navigationBarsPadding()
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight(),
            navController = navController,
            startDestination = Screen.Welcome.route,
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
            ){
                WelcomeScreen(navController)
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
            ){
                LoginScreen(navController = navController)
            }
            composable(
                route = Screen.Home.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    )
                },
            ){
                HomeScreen(navController)
            }
            composable(Screen.MyCart.route) { MyCartScreen(navController) }
            composable(Screen.Coupon.route){ CouponScreen() }
            composable(Screen.Favourite.route){ FavouriteScreen() }
            composable(Screen.Profile.route){ ProfileScreen() }
            composable(Screen.OurProduct.route){ OurProductScreen(navController) }
            composable(Screen.Categories.route){ CategoriesScreen(navController) }
            composable(
                route = Screen.DetailProduct.route,
                arguments = listOf(
                    navArgument("productId") {
                        type = NavType.IntType
                    }
                ),
            ){ backStackEntry ->
                val id = backStackEntry.arguments?.getInt("productId") ?: -2
                DetailScreen(
                    productId = id,
                    navController
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VibeStoreAppPreview() {
    VibeStoreTheme {
        VibeStoreApp()
    }
}