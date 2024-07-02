package com.example.vibestore.ui.screen.ourproduct

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.ui.theme.VibeStoreTheme


@Composable
fun OurProductScreen(
    navcontroller: NavHostController
) {
//    val viewModel: OurProductViewModel = viewModel()
//    val products by viewModel.product.observeAsState(emptyList())
//    Scaffold(
//        topBar = {
//            Search()
//        }
//    ) { innerPadding ->
//        if (products.isEmpty()){
//            Box(
//                modifier = Modifier
//                    .padding(innerPadding)
//                    .fillMaxSize()
//            ){
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .width(64.dp)
//                        .align(Alignment.Center),
//                    color = Color("#29bf12".toColorInt()),
//                )
//            }
//        } else {
//            LazyVerticalGrid(
//                columns = GridCells.Adaptive(196.dp),
//                modifier = Modifier
//                    .padding(innerPadding)
//                    .fillMaxSize()
//            ) {
//                items(products) { product ->
//                    ProductCard(
//                        image = product.image,
//                        title = product.title,
//                        modifier = Modifier
//                            .clickable {
//                                navcontroller.navigate(Screen.DetailProduct.createRoute(product.id))
//                            }
//                    )
//                }
//            }
//        }
//    }
}

@Preview(showBackground = true)
@Composable
private fun MyProductPreview() {
    VibeStoreTheme {
        OurProductScreen(rememberNavController())
    }
}