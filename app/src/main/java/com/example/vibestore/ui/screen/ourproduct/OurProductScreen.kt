package com.example.vibestore.ui.screen.ourproduct

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vibestore.ui.component.ProductCard
import com.example.vibestore.ui.component.Search
import com.example.vibestore.ui.theme.VibeStoreTheme


@Composable
fun OurProductScreen(
    navigateToDetail: (Int) -> Unit
) {
    val viewModel: OurProductViewModel = viewModel()
    val products by viewModel.product.observeAsState(emptyList())
    Scaffold(
        topBar = {
            Search()
        }
    ) { innerPadding ->
        if (products.isEmpty()){
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ){
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center),
                    color = Color("#29bf12".toColorInt()),
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(196.dp),
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                items(products) { product ->
                    ProductCard(
                        image = product.image,
                        title = product.title,
                        price = product.price,
                        modifier = Modifier
                            .clickable {
                                navigateToDetail(product.id)
                            }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyProductPreview() {
    VibeStoreTheme {
        OurProductScreen(navigateToDetail = {})
    }
}