package com.example.vibestore.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.vibestore.ui.component.ImageSlider
import com.example.vibestore.ui.component.Search
import com.example.vibestore.ui.component.TabCategory

@Composable
fun HomeScreen() {
    Column {
        Search()
        ImageSlider()
        TabCategory()
    }
}