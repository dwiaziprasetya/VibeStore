package com.example.vibestore.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.vibestore.ui.component.ImageSlider
import com.example.vibestore.ui.component.Search
import com.example.vibestore.ui.component.TabCategory

@Composable
fun HomeScreen() {
    Scaffold (
        topBar = {
            Search()
        }
    ) { innerPading ->
        Column(
            modifier = Modifier.padding(innerPading)
        ) {
            ImageSlider()
            TabCategory()
        }
    }
}