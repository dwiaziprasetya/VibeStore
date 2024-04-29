package com.example.vibestore.ui.screen.product.all

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.component.AnimatedShimmerProduct
import com.example.vibestore.ui.component.ProductCard
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun AllProductScreen(
    gridHeight: Dp = Dp.Unspecified,
    limit: Int,
    height: Dp,
    count: Int = 4,
    navigateToDetail: (Int) -> Unit
) {
    val viewModel: AllProductViewModel = viewModel(
        factory = ViewModelFactory(limit)
    )
    val product by viewModel.products.observeAsState(emptyList())

    Log.d("Product Content", "Ini dipanggil")
    if (product.isEmpty()){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(height)
        ){
            LazyVerticalGrid(
                columns = GridCells.Adaptive(196.dp),
                modifier = Modifier.heightIn(min = gridHeight, max = gridHeight)
            ) {
                items(count){
                    AnimatedShimmerProduct()
                }
            }
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(196.dp),
            modifier = Modifier.heightIn(min = gridHeight, max = gridHeight)
        ) {
            items(product){
                ProductCard(
                    image = it.image,
                    title = it.title,
                    modifier = Modifier.clickable {
                        navigateToDetail(it.id)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AllProductScreenPreview() {
    VibeStoreTheme {
        AllProductScreen(limit = 20, navigateToDetail = {},
            height = 548.dp)
    }
}

@Composable
fun ProductContent() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(196.dp),
        modifier = Modifier.height(548.dp),
    ) {
        items(4){
            ProductCard(
                image = "MU Jersey",
                title = "MU Jersey",
            )
        }
    }
}

@Composable
fun ProductContent2() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(275.dp)
    ){
        items(6){
            ProductCard(
                image = "",
                title = "Mu Jersey",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductContentPreview() {
    VibeStoreTheme {
        ProductContent()
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductContentPreview2() {
    VibeStoreTheme {
        ProductContent2()
    }
}

