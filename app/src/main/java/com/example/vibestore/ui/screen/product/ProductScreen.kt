package com.example.vibestore.ui.screen.product

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vibestore.ui.component.ProductCard

@Composable
fun ProductScreen(
    viewModel: ProductViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val product by viewModel.products.observeAsState(emptyList())

    Log.d("Product Content", "Ini dipanggil")
    if (product.isEmpty()){
        Box(modifier = Modifier.fillMaxSize()){
            Text(text = "Loading", modifier = Modifier.align(Alignment.Center))
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(196.dp),
            modifier = Modifier.wrapContentHeight(),
        ) {
            items(product){
                ProductCard(
                    image = it.image,
                    title = it.title,
                    price = it.price
                )
            }
        }
    }
}

