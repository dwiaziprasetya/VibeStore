package com.example.vibestore.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun HomeSection(
    title: String,
    title2: String = "See all",
    content: @Composable () -> Unit,
    navigateToMyProduct: () -> Unit,
) {
    Column {
        if (title != ""){
            SectionText(title, title2, navigateToMyProduct)
        }
        content()
    }
}