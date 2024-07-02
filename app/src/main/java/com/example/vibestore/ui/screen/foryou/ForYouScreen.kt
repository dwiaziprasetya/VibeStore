package com.example.vibestore.ui.screen.foryou

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.ui.component.AnimatedShimmerProduct
import com.example.vibestore.ui.component.ProductCard
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun ForYouScreen(
    navigateToDetail: (Int) -> Unit
) {

    val forYouViewModel: ForYouProductViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current,
            limit = 6
        )
    )


    val forYouProduct by forYouViewModel.products.observeAsState(emptyList<ProductResponseItem>().shuffled())

    if (forYouProduct.isEmpty()){
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
    } else {
        LazyRow {
            items(forYouProduct){ product ->
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
}

@Preview(showBackground = true)
@Composable
private fun ForYouScreenPreview() {
    VibeStoreTheme {
        ForYouScreen(navigateToDetail = {})
    }
}