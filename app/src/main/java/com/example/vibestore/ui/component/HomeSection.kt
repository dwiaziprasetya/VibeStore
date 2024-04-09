package com.example.vibestore.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    navigateToMyProduct: () -> Unit
) {
    Column {
        SectionText(title, navigateToMyProduct)
        content()
    }
}