package com.example.vibestore

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import com.example.vibestore.ui.screen.coupon.CouponScreen
import com.example.vibestore.ui.screen.detail.DetailScreen
import com.example.vibestore.ui.screen.favourite.FavouriteScreen
import com.example.vibestore.ui.screen.home.HomeScreen
import com.example.vibestore.ui.screen.mycart.MyCartScreen
import com.example.vibestore.ui.screen.ourproduct.OurProductScreen
import com.example.vibestore.ui.screen.profile.ProfileScreen
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun VibeStoreApp(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route



    Scaffold(
        bottomBar = {
            if (currentRoute !in listOf(
                    Screen.OurProduct.route,
                    Screen.DetailProduct.route,
                    Screen.MyCart.route
            )
                ){
                BottomNavigation(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { fadeIn(tween(500)) },
            exitTransition = { fadeOut(tween(500)) }
        ) {
            composable(
                route = Screen.Home.route,
            ){
                HomeScreen(
                    navigateToMyProduct = {
                        navController.navigate(Screen.OurProduct.route)
                    },
                    navigateToDetail = { productId ->
                        navController.navigate(Screen.DetailProduct.createRoute(productId))
                    }
                )
            }
            composable(Screen.MyCart.route){
                MyCartScreen(onBackClick = {
                    navController.navigateUp()
                })
            }
            composable(Screen.Coupon.route){
                CouponScreen()
            }
            composable(Screen.Favourite.route){
                FavouriteScreen()
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
            composable(Screen.OurProduct.route){
                OurProductScreen(
                    onBackClick = { navController.navigateUp() },
                    navigateToDetail = { productId ->
                        navController.navigate(Screen.DetailProduct.createRoute(productId))
                    }
                )
            }
            composable(
                route = Screen.DetailProduct.route,
                arguments = listOf(navArgument("productId") { type = NavType.IntType }),
            ){
                val id = it.arguments?.getInt("productId") ?: -2
                DetailScreen(
                    productId = id,
                    onBackClick = {
                        navController.navigateUp()
                    },
                    navigateToCart = {
                        navController.navigate(Screen.MyCart.route)
                    }
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