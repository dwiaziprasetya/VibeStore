package com.example.vibestore.ui.screen.mycart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
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
    val totalPrice by viewModel.totalPrice.observeAsState(0.0)
    var checkedValue by remember { mutableStateOf(false) }
    var checkedAllValue by remember { mutableStateOf(false) }
    val selectedCartItems by viewModel.selectedCartItems.observeAsState(emptySet())
    val context = LocalContext.current

    LaunchedEffect(cartItems.size, selectedCartItems.size) {
        checkedAllValue = if (cartItems.isNotEmpty()) {
            selectedCartItems.size == cartItems.size
        } else {
            false
        }
    }

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
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
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
                    selectedCartItems = selectedCartItems,
                    state = cartItems,
                    onQuantityChange = { cart, quantity ->
                        viewModel.updateQuantity(cart, quantity)
                    },
                    totalPrice = totalPrice,
                    navController = navHostController,
                    onCheckedChange = { cart, isChekced ->
                        viewModel.updateCheckedItem(cart, isChekced)
                    },
                    checkedAllValue = checkedAllValue,
                    addOrder = {
                        viewModel.createOrderFromSelectedItems(context = context)
                        navHostController.navigate(Screen.Checkout.route)
                    },
                    onCheckedAllChange = { isChecked ->
                        cartItems.forEach { item ->
                            viewModel.updateCheckedItem(item, isChecked)
                        }
                        checkedValue = isChecked
                    }
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
    totalPrice: Double,
    selectedCartItems: Set<Cart>,
//    checkedValue: Boolean,
    checkedAllValue: Boolean,
    onCheckedChange: (Cart, Boolean) -> Unit,
    onCheckedAllChange: (Boolean) -> Unit,
    addOrder: () -> Unit,
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
                        checkedValue = checkedAllValue || cartItem.id in selectedCartItems.map { it.id },
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.DetailProduct.createRoute(cartItem.productId))
                            },
                        onCheckedChange = { isChecked ->
                            onCheckedChange(cartItem, isChecked)
                        },
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
            Divider()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                RoundedCornerCheckbox(
                    label = "Select All",
                    isChecked = checkedAllValue,
                    onValueChange = onCheckedAllChange,
                    modifier = Modifier
                        .padding(end = 32.dp)
                )
                Column(modifier = Modifier.weight(1f)){
                    Text(
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        text = "Total",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        text = "\$${"%.2f".format(totalPrice)}"
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    modifier = Modifier
                        .height(55.dp)
                        .width(120.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = addOrder,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        fontFamily = poppinsFontFamily,
                        text = stringResource(R.string.checkout),
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun RoundedCornerCheckbox(
    label: String,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    size: Float = 20f,
    checkedColor: Color = MaterialTheme.colorScheme.primary,
    uncheckedColor: Color = MaterialTheme.colorScheme.background,
    onValueChange: (Boolean) -> Unit
) {
    val checkboxColor: Color by animateColorAsState(if (isChecked) checkedColor else uncheckedColor,
        label = ""
    )
    val density = LocalDensity.current
    val duration = 200

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .toggleable(
                value = isChecked,
                role = Role.Checkbox,
                onValueChange = onValueChange
            )
    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .background(
                    color = checkboxColor,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(
                    width = 1.5.dp,
                    color = if (isChecked) checkedColor else MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(4.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = isChecked,
                enter = slideInHorizontally(animationSpec = tween(duration)) {
                    with(density) { (size * -0.5).dp.roundToPx() }
                } + expandHorizontally(
                    expandFrom = Alignment.Start,
                    animationSpec = tween(duration)
                ),
                exit = fadeOut()
            ) {
                androidx.compose.material.Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = uncheckedColor
                )
            }
        }
        if (label != "") {
            androidx.compose.material.Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = label,
                fontFamily = poppinsFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
            )
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
            totalPrice = 4567323.00,
            navController = rememberNavController(),
            onCheckedChange = { _, _ ->
            },
            addOrder = {},
            onCheckedAllChange = {  },
            checkedAllValue = false,
            selectedCartItems = emptySet()
        )
    }
}