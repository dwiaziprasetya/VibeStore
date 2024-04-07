package com.example.vibestore.ui.navigation

import androidx.compose.ui.graphics.painter.Painter

data class NavigationItem(
    val title: String,
    val icon: Painter,
    val iconActive: Painter,
    val screen: Screen
)