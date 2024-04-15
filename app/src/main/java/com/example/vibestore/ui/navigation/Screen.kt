package com.example.vibestore.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Categories : Screen("categories")
    object Coupon : Screen("coupon")
    object Favourite : Screen("favourite")
    object Profile : Screen("profile")
    object OurProduct : Screen("myproduct")
    object MyCart : Screen("mycart")
    object DetailProduct : Screen("home/{productId}") {
        fun createRoute(productId: Int) = "home/$productId"
    }
}
