package com.example.vibestore

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.navigation.graph.authNav
import com.example.vibestore.ui.navigation.model.Screen
import com.example.vibestore.ui.screen.main.MainScreen
import com.example.vibestore.ui.screen.main.MainViewModel
import com.example.vibestore.ui.theme.VibeStoreTheme
import kotlinx.coroutines.delay

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current
        )
    ),
    onDataLoaded: () -> Unit
) {

    var fakeLoading by remember { mutableStateOf(true) }
    val session by viewModel.getSession().observeAsState()

    LaunchedEffect(Unit) {
        delay(1000)
        fakeLoading = false
        onDataLoaded()
    }

    if (!fakeLoading) {
        when {
            session!!.token.isEmpty() -> {
                NavHost(
                    navController = navController,
                    route = Screen.Root.route,
                    startDestination = Screen.AuthNav.route
                ) {
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
            else -> {
                NavHost(
                    navController = navController,
                    route = Screen.Root.route,
                    startDestination = Screen.MainNav.route
                ) {
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
        }
    }
}

@Preview
@Composable
private fun RootNavigationGraphPreview() {
    VibeStoreTheme {
        RootNavigationGraph(navController = rememberNavController()) {}
    }
}