package com.example.vibestore.ui.screen.ourproduct

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.ui.common.UiState
import com.example.vibestore.ui.component.AnimatedShimmerProduct
import com.example.vibestore.ui.component.ProductCard
import com.example.vibestore.ui.component.SearchBar
import com.example.vibestore.ui.navigation.Screen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OurProductScreen(
    navcontroller: NavHostController,
    viewModel: OurProductViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory
            .getInstance(LocalContext.current))
) {
    var sortValue by remember { mutableStateOf("asc") }
    val uiState by viewModel.uiState.observeAsState(initial = UiState.Loading)

    Scaffold(
        topBar = {
            SearchBar(
                onSearch = { query ->
                    if (query.isEmpty()) {
                        viewModel.sortProduct(sortValue)
                    } else {
                        viewModel.searchProduct(query)
                    }
                },
                onSortChange = { sort ->
                    if (sort == "asc") sortValue = "asc" else sortValue = "desc"
                    viewModel.sortProduct(sort)
                }
            )
        }
    ) { innerPadding ->
        when(uiState) {
            is UiState.Loading -> {
                viewModel.getAllProduct(20)
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                ){
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(196.dp),
                        modifier = Modifier.heightIn(min = 1000.dp, max = 1000.dp)
                    ) {
                        items(10){
                            AnimatedShimmerProduct()
                        }
                    }
                }
            }
            is UiState.Success -> {
                val products = (uiState as UiState.Success<List<ProductResponseItem>>).data
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(196.dp),
                    modifier = Modifier
                        .padding(innerPadding)
                        .imePadding()
                        .fillMaxSize()
                ) {
                    items(items = products) { item ->
                        ProductCard(
                            image = item.image,
                            title = item.title,
                            modifier = Modifier
                                .clickable {
                                    navcontroller.navigate(
                                        Screen
                                            .DetailProduct
                                            .createRoute(item.id)
                                    )
                                }
                        )
                    }
                }
            }
            is UiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    Text(
                        text = (uiState as UiState.Error).errorMessage,
                        fontFamily = poppinsFontFamily,
                        modifier = Modifier
                            .align(Alignment.Center)
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
        OurProductScreen(rememberNavController())
    }
}