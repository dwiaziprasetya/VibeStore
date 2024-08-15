package com.example.vibestore.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.vibestore.R
import com.example.vibestore.helper.DialogHelper
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.ui.common.UiState
import com.example.vibestore.ui.component.AnimatedShimmerDetailProduct
import com.example.vibestore.ui.component.ExpandingText
import com.example.vibestore.ui.navigation.model.Screen
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    productId: Int,
    navcontroller: NavHostController,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current,
        )
    )
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.observeAsState(initial = UiState.Loading)
    val favouriteItems by viewModel.favouriteItems.observeAsState(emptyList())
    val isProductFavorited by viewModel.isProductFavorited(productId).observeAsState(false)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                title = {
                    Text(
                        text = stringResource(R.string.product_detail),
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.Black,
                        contentDescription = "back",
                        modifier = Modifier.clickable { navcontroller.navigateUp() }
                    )
                },
                actions = {
                    Icon(
                        painter = painterResource(
                            id = if (isProductFavorited) R.drawable.icon_favourite_filled_red
                            else R.drawable.icon_favourite_outlined
                        ),
                        tint = Color.Unspecified,
                        contentDescription = "favourite",
                        modifier = Modifier
                            .clickable {
                                if (isProductFavorited) {
                                    favouriteItems.find { it.productId == productId }?.let {
                                        viewModel.deleteFavouriteById(it, context)
                                    }
                                } else {
                                    viewModel.addToFavourite(
                                        (uiState as UiState.Success<ProductResponseItem>).data,
                                        context
                                    )
                                }
                            }
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPading ->
        when (uiState) {
            is UiState.Loading -> {
                AnimatedShimmerDetailProduct(
                    modifier = Modifier.padding(innerPading)
                )
                viewModel.getSingleProduct(productId)
            }
            is UiState.Success -> {
                val data = (uiState as UiState.Success<ProductResponseItem>).data
                DetailContent(
                    product = data,
                    paddingValues = innerPading,
                    addCart = {
                        viewModel.addToCart(data)
                        DialogHelper.showDialogSuccess(
                            context = context,
                            title = "Success",
                            textContent = "Product successfully added to cart",
                            textConfirm = "See Cart",
                            textConfirmSize = 13f,
                            onConfirm = {
                                navcontroller.navigate(Screen.MyCart.route)
                            }
                        )
                    },
                )
            }
            is UiState.Error -> {
                Box(modifier = Modifier.fillMaxSize()){
                    Text(text = (uiState as UiState.Error).errorMessage)
                }
            }
        }
    }
}

@Composable
fun DetailContent(
    product: ProductResponseItem,
    addCart: () -> Unit,
    paddingValues: PaddingValues,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = product.image,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp,
                    )
                    .height(300.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentDescription = "Product Image"
            )
            Text(
                modifier = Modifier.padding(
                    top = 16.dp,
                ),
                text = product.title,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp
            )
            Row (
                modifier = Modifier.padding(top = 8.dp)
            ){
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color("#FFB000".toColorInt())
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = product.rating.rate.toString(),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = "(${product.rating.count})",
                    color = MaterialTheme.colorScheme.outline,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Light
                )
            }
            Text(
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 32.dp),
                text = stringResource(R.string.description_product),
                fontFamily = poppinsFontFamily
            )
            ExpandingText(
                modifier = Modifier.padding(top = 8.dp),
                text = product.description,
                fontSize = (13.5).sp
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(85.dp)
                .background(MaterialTheme.colorScheme.background)
        ){
            Divider()
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .weight(
                            1f
                        ),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 40.sp,
                    text = "$${product.price}"
                )
                Button(
                    modifier = Modifier
                        .height(55.dp)
                        .width(170.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = addCart,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        fontFamily = poppinsFontFamily,
                        text = stringResource(R.string.add_to_cart),
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                }
            }
        }
    }
}
