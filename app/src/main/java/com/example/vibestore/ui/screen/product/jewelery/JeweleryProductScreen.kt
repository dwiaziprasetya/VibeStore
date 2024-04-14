package com.example.vibestore.ui.screen.product.jewelery

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.component.ProductCard
import com.example.vibestore.ui.screen.product.all.AllProductScreen
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun JeweleryProductScreen(
    gridHeight: Dp = Dp.Unspecified,
    limit: Int,
    navigateToDetail: (Int) -> Unit
) {

    val viewModel2: JeweleryProductViewModel = viewModel(
        factory = ViewModelFactory(limit)
    )
    val product by viewModel2.products.observeAsState(emptyList())

    Log.d("Product Content", "Ini dipanggil")
    if (product.isEmpty()){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(548.dp)
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
            modifier = Modifier.heightIn(min = gridHeight, max = gridHeight),
        ) {
            items(product){
                ProductCard(
                    image = it.image,
                    title = it.title,
                    price = it.price,
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
private fun JeweleryProductScreenPreview() {
    VibeStoreTheme {
        AllProductScreen(limit = 20, navigateToDetail = {})
    }
}

