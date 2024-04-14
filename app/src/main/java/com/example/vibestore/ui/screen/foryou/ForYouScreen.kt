package com.example.vibestore.ui.screen.foryou

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.ui.component.ProductCard

@Composable
fun ForYouScreen(
    navigateToDetail: (Int) -> Unit
) {

    val forYouViewModel: ForYouProductViewModel = viewModel(
        factory = ViewModelFactory(6)
    )


    val forYouProduct by forYouViewModel.products.observeAsState(emptyList<ProductResponseItem>().shuffled())

    LazyRow {
        items(forYouProduct){ product ->
            ProductCard(
                image = product.image,
                title = product.title,
                price = product.price,
                modifier = Modifier.clickable {
                    navigateToDetail(product.id)
                }
            )
        }
    }
}