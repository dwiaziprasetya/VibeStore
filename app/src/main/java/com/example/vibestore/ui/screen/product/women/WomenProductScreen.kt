package com.example.vibestore.ui.screen.product.women

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.common.UiState
import com.example.vibestore.ui.component.AnimatedShimmerProduct
import com.example.vibestore.ui.component.ProductCard2

@Composable
fun WomenProductScreen(
    gridHeight: Dp = Dp.Unspecified,
    limit: Int,
    height: Dp,
    count: Int = 4,
    navigateToDetail: (Int) -> Unit,
    viewmodel: WomenProductViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current,
            limit = limit
        )
    )
) {
    viewmodel.uiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewmodel.getProductByCategory("women's clothing", limit)
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
            }
            is UiState.Success -> {
                val product = uiState.data
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(196.dp),
                    modifier = Modifier.heightIn(min = gridHeight, max = gridHeight),
                ) {
                    items(product){
                        ProductCard2(
                            image = it.image,
                            title = it.title,
                            modifier = Modifier.clickable {
                                navigateToDetail(it.id)
                            },
                            rating = it.rating.rate.toString(),
                            price = it.price.toString(),
                            category = it.category,
                            addToCart = { viewmodel.addToCart(it) }
                        )
                    }
                }
            }
            is UiState.Error -> {
                Box(modifier = Modifier.fillMaxWidth()){
                    Text(text = uiState.errorMessage)
                }
            }
        }
    }
}

