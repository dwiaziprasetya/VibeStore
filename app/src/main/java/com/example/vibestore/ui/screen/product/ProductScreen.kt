package com.example.vibestore.ui.screen.product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vibestore.ui.component.ProductCard
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun ProductScreen(
    viewModel: ProductViewModel,
) {
    val product by viewModel.product.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.getAllProduct()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (product.isEmpty()) {
            Text(text = "Loading....", modifier = Modifier.align(Alignment.Center))
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(196.dp),
                modifier = Modifier.wrapContentHeight(),
            ) {
                items(product){
                    ProductCard(it)
                }
            }
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
private fun AllScreenPreview() {
    VibeStoreTheme {
        ProductScreen(viewModel = viewModel())
    }
}