package com.example.vibestore.ui.navigation

sealed class Screen(val route: String) {

    // Screen Routes
    data object Welcome : Screen("welcome")
    data object SignUp : Screen("signup")
    data object Login : Screen("login")
    data object Home : Screen("home")
    data object Categories : Screen("categories")
    data object Coupon : Screen("coupon")
    data object Favourite : Screen("favourite")
    data object Profile : Screen("profile")
    data object OurProduct : Screen("myproduct")
    data object MyCart : Screen("mycart")
    data object DetailProduct : Screen("home/{productId}") {
        fun createRoute(productId: Int) = "home/$productId"
    }

    // Graph Routes
    data object AuthNav : Screen("AUTH_NAV_GRAPH")
    data object MainNav : Screen("MAIN_NAV_GRAPH")

}
