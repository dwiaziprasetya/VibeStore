package com.example.vibestore.ui.screen.foryou

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.common.UiState
import com.example.vibestore.ui.component.AnimatedShimmerProduct
import com.example.vibestore.ui.component.ProductCard
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun ForYouScreen(
    navigateToDetail: (Int) -> Unit,
    viewModel: ForYouViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current,
        )
    )
) {
    viewModel.uiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getProductByCategory("women's clothing", 6)
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(275.dp)
                ){
                    LazyRow {
                        items(4){
                            AnimatedShimmerProduct()
                        }
                    }
                }
            }
            is UiState.Success -> {
                val forYouProduct = uiState.data
                LazyRow {
                    items(forYouProduct) { product ->
                        ProductCard(
                            image = product.image,
                            title = product.title,
                            modifier = Modifier.clickable {
                                navigateToDetail(product.id)
                            }
                        )
                    }
                }
            }
            is UiState.Error -> {
                Box(modifier = Modifier.fillMaxSize()){
                    Text(text = uiState.errorMessage)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ForYouScreenPreview() {
    VibeStoreTheme {
        ForYouScreen(navigateToDetail = {})
    }
}