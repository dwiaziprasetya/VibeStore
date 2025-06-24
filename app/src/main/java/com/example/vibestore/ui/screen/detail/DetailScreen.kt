package com.example.vibestore.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
import com.example.vibestore.model.Rating
import com.example.vibestore.ui.common.UiState
import com.example.vibestore.ui.component.AnimatedShimmerDetailProduct
import com.example.vibestore.ui.component.ExpandingText
import com.example.vibestore.ui.navigation.model.Screen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily
import kotlinx.coroutines.launch

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
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val uiState by viewModel.uiState.observeAsState(initial = UiState.Loading)
    val favouriteItems by viewModel.favouriteItems.observeAsState(emptyList())
    val isProductFavorited by viewModel.isProductFavorited(productId).observeAsState(false)

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
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
                                        viewModel.deleteFavouriteById(it)
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = "Product removed from favourites",
                                            )
                                        }
                                    }
                                } else {

                                    val data = (uiState as? UiState.Success<ProductResponseItem>)?.data
                                    if (data != null) {
                                        viewModel.addToFavourite(data)
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = "Product added to favourites",
                                            )
                                        }
                                    } else {
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = "Please wait, product is still loading",
                                            )
                                        }
                                    }

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
                    rating = data.rating.rate.toString()
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
    rating: String,
    paddingValues: PaddingValues,
) {
    val ratingState by remember { mutableFloatStateOf(rating.toFloat()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 90.dp
                )
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
                modifier = Modifier.padding(top = 16.dp),
                text = product.title,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp
            )
            Row (
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                RatingBar(
                    rating = ratingState,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .offset(y = 3.dp),
                    text = "${product.rating.rate}/5",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
                Divider(
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(20.dp)
                        .width(1.dp)
                )
                Text(
                    modifier = Modifier
                        .offset(y = 3.dp),
                    text = "(${product.rating.count})",
                    color = MaterialTheme.colorScheme.outline,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Light
                )
                Divider(
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(20.dp)
                        .width(1.dp)
                )
                Text(
                    modifier = Modifier
                        .offset(y = 3.dp),
                    maxLines = 1,
                    fontSize = 14.sp,
                    text = product.category,
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = poppinsFontFamily,
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
                fontSize = (14).sp
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            fontFamily = poppinsFontFamily,
                            text = stringResource(R.string.add_to_cart),
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.offset(y = 2.dp)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Icon(
                            painter = painterResource(R.drawable.icon_cart_outlined),
                            contentDescription = "Cart",
                            tint = MaterialTheme.colorScheme.background,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RatingBar(
    rating: Float,
    modifier: Modifier = Modifier,
    starCount: Int = 5,
    filledStarColor: Color = Color("#FFB000".toColorInt()),
    unfilledStarColor: Color = MaterialTheme.colorScheme.outline,
) {
    Row(modifier = modifier) {
        for (i in 1..starCount) {
            val starFraction = rating - i + 1
            val iconTint = when {
                starFraction >= 1 -> filledStarColor
                else -> unfilledStarColor
            }
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier
                    .size(20.dp)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    VibeStoreTheme(
        dynamicColor = false
    ) {
        DetailContent(
            product = ProductResponseItem(
                id = 1,
                title = "Nasi Padang",
                price = 23.22,
                description = "Makanan khas Indonesia",
                category = "Makanan",
                image = "",
                rating = Rating(
                    count = 200,
                    rate = 3.5
                )
            ),
            addCart = {  },
            rating = "3.9",
            paddingValues = PaddingValues()
        )
    }
}
