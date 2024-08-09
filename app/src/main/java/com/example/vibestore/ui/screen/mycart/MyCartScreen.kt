package com.example.vibestore.ui.screen.mycart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.R
import com.example.vibestore.data.local.entity.Cart
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.component.CartItem
import com.example.vibestore.ui.navigation.model.Screen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCartScreen(
    navHostController: NavHostController,
    viewModel: MyCartViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current,
        )
    ),
) {

    val cartItems by viewModel.cartItems.observeAsState(emptyList())
    val subTotalPrice by viewModel.totalPrice.observeAsState(0.0)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .statusBarsPadding(),
                title = {
                    Text(
                        text = stringResource(R.string.my_cart),
                        fontFamily = poppinsFontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back",
                        modifier = Modifier
                            .clickable {
                                navHostController.navigateUp()
                            }
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (cartItems.isEmpty()) {
                Text(
                    text = "Cart is empty",
                    modifier = Modifier.align(Alignment.Center),
                    fontFamily = poppinsFontFamily
                )
            } else {
                MyCartContent(
                    state = cartItems,
                    onQuantityChange = { cart, quantity ->
                        viewModel.updateQuantity(cart, quantity)
                    },
                    subTotalPrice = subTotalPrice,
                    totalPrice = subTotalPrice + 13.0,
                    navController = navHostController
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyCartContent(
    navController: NavController,
    state: List<Cart>,
    onQuantityChange: (Cart, Int) -> Unit,
    modifier: Modifier = Modifier,
    subTotalPrice: Double,
    totalPrice: Double,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        var visible by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            visible = true
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(items = state, key = { it.id }) { cartItem ->
                AnimatedVisibility(
                    modifier = Modifier.animateItemPlacement(tween(100)),
                    visible = visible,
                    enter = slideInVertically(
                        initialOffsetY = { -it },
                        animationSpec = tween(durationMillis = 300)
                    ) + fadeIn(animationSpec = tween(durationMillis = 300))
                ) {
                    CartItem(
                        imageId = cartItem.productImage,
                        productName = cartItem.productName,
                        category = cartItem.productCategory,
                        price = cartItem.productPrice,
                        orderCount = cartItem.productQuantity,
                        onQuantityChange = { newQuantity ->
                            onQuantityChange(cartItem, newQuantity)
                        },
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.DetailProduct.createRoute(cartItem.productId))
                            }
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Divider()
            Column(
                modifier = Modifier.padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        fontFamily = poppinsFontFamily,
                        text = "Sub-total",
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        fontFamily = poppinsFontFamily,
                        text = "\$${"%.2f".format(subTotalPrice)}",
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        fontFamily = poppinsFontFamily,
                        text = "Shipping Charge",
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        fontFamily = poppinsFontFamily,
                        text = "$13.00",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 40.sp,
                    text = "\$${"%.2f".format(totalPrice)}"
                )
                Button(
                    modifier = Modifier
                        .height(55.dp)
                        .width(170.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        fontFamily = poppinsFontFamily,
                        text = stringResource(R.string.checkout),
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}



@Preview(
    showBackground = true,
    device = Devices.PIXEL_4_XL
)
@Composable
private fun MyCartScreenPreview() {
    VibeStoreTheme(dynamicColor = false) {
        MyCartContent(
            state = listOf(
                Cart(
                    productId = 1,
                    productName = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    productPrice = "78",
                    productImage = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                    productCategory = "men's clothing",
                    productQuantity = 1
                )
            ),
            onQuantityChange = { _, _ ->},
            subTotalPrice = 0.0,
            totalPrice = 0.0,
            navController = rememberNavController()
        )
    }
}