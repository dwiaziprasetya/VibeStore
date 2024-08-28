package com.example.vibestore.ui.screen.checkout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vibestore.R
import com.example.vibestore.data.local.DataDummy
import com.example.vibestore.data.local.entity.Cart
import com.example.vibestore.data.local.entity.Order
import com.example.vibestore.data.local.entity.UserLocation
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.model.Coupon
import com.example.vibestore.model.Shipping
import com.example.vibestore.ui.component.AddressItemScreen
import com.example.vibestore.ui.component.CartItemMini
import com.example.vibestore.ui.component.CouponInactiveSelected
import com.example.vibestore.ui.component.CouponItem
import com.example.vibestore.ui.component.CouponItemSelected
import com.example.vibestore.ui.component.ShippingItem
import com.example.vibestore.ui.navigation.model.Screen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    navController: NavController,
    viewModel: CheckoutViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current,
        )
    ),
    selectedLocationId: Int
) {

    val latestOrder by viewModel.orderItems.observeAsState()
    var showDialog by remember { mutableStateOf(false) }
    var isSheetShippingOpen by remember { mutableStateOf(false) }
    var isSheetCouponOpen by remember { mutableStateOf(false) }
    val selectedLocation by viewModel.getUserLocationById(selectedLocationId).observeAsState()
    val shippingItem = DataDummy.dummyShipping
    val couponItem = DataDummy.dummyCoupon
    val selectedCouponId by viewModel.selectedCouponId.observeAsState()
    val selectedShippingId by viewModel.selectedShippingId.observeAsState()
    val isButtonEnabled = (selectedLocationId != -1 && selectedShippingId != null)

    if (isSheetShippingOpen) {
        BottomSheetShipping(
            onDismiss = { isSheetShippingOpen = false },
            state = shippingItem,
            selectedShippingId = selectedShippingId,
            onChoose = { id -> viewModel.selectShipping(id) },
            onConfirmationShipping = { isSheetShippingOpen = false }
        )
    }

    if (isSheetCouponOpen) {
        BottomSheetCoupon(
            onDismiss = { isSheetCouponOpen = false },
            state = couponItem,
            selectedCouponId = selectedCouponId,
            onChoose = { id -> viewModel.selectCoupon(id) },
            onConfirmationCoupon = { isSheetCouponOpen = false }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .statusBarsPadding(),
                title = {
                    Text(
                        text = stringResource(R.string.checkout),
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
                                navController.navigateUp()
                            }
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        CheckoutContent(
            modifier = Modifier.padding(innerPadding),
            onEditAddress = {
                navController.navigate(Screen.Address.route)
            },
            state = latestOrder,
            onShowDialog = { showDialog = true },
            onChooseShipping = { isSheetShippingOpen = true },
            selectedLocation = selectedLocation ?: UserLocation(0, "", ""),
            selectedLocationId = selectedLocationId,
            selectedShippingId = selectedShippingId,
            shippingItem = shippingItem,
            onChooseCoupon = { isSheetCouponOpen = true },
            selectedCouponId = selectedCouponId,
            couponItem = couponItem,
            isButtonEnabled = isButtonEnabled
        )

        if (showDialog) {
            CartItemDialog(
                items = latestOrder?.items ?: emptyList(),
                onDismiss = { showDialog = false }
            )
        }
    }
}

@Composable
fun CheckoutContent(
    modifier: Modifier = Modifier,
    onEditAddress: () -> Unit,
    state: Order ?= null,
    selectedLocationId: Int?,
    selectedLocation: UserLocation,
    onShowDialog: () -> Unit,
    onChooseShipping: () -> Unit,
    onChooseCoupon: () -> Unit,
    selectedShippingId: Int?,
    shippingItem: List<Shipping>,
    selectedCouponId: Int?,
    couponItem: List<Coupon>,
    isButtonEnabled: Boolean = false,
) {
    val subTotal = state?.totalPrice ?: 0.0
    val shippingCost = selectedShippingId?.let {
        shippingItem[it].price
    } ?: 0.0

    val selectedCoupon = selectedCouponId?.let { couponItem[it] }
    val finalPrice = calculateFinalPrice(
        subTotal = subTotal,
        shippingPrice = shippingCost,
        coupon = selectedCoupon
    )

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .weight(1f)
        ) {
            if (selectedLocationId == -1) {
                Card(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.LightGray
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                        .clickable { onEditAddress() }
                        .wrapContentHeight()
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.Center),
                            text = "Choose Address",
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
            } else {
                Card(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.LightGray
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .clickable { onEditAddress() }
                        .height(125.dp)
                ) {
                    AddressItemScreen(
                        name = selectedLocation.name,
                        address = selectedLocation.address
                    )
                }
            }
            state?.items?.firstOrNull()?.let { item ->
                CartItemMini(
                    productName = item.productName,
                    imageId = item.productImage,
                    price = item.productPrice,
                    orderCount = item.productQuantity,
                    totalOrder = state.items.size,
                    onDetailOrder = { onShowDialog() }
                )
            }
            if (selectedShippingId == null) {
                Card(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.LightGray
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable {
                            onChooseShipping()
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Choose Shipping",
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Edit",
                                fontSize = 14.sp,
                                fontFamily = poppinsFontFamily,
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }
                    }
                }
            } else {
                val selectedShipping = shippingItem[selectedShippingId]
                Card(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.LightGray
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable {
                            onChooseShipping()
                        }
                ) {
                    ShippingItem(
                        isChoose = true,
                        onChoose = { onChooseShipping() },
                        name = selectedShipping.name,
                        price = selectedShipping.price,
                        description = selectedShipping.description
                    )
                }
            }
            if (selectedCouponId == null) {
                CouponInactiveSelected(
                    modifier = Modifier
                        .clickable { onChooseCoupon() }
                )
            } else {
                val selectedCouponChoose = couponItem[selectedCouponId]
                CouponItemSelected(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable { onChooseCoupon() },
                    discountTittle = selectedCouponChoose.discountedPrice
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            ) {
                Text(
                    fontFamily = poppinsFontFamily,
                    text = "Payment Summary",
                    fontWeight = FontWeight.SemiBold
                )
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
                        text = "$${state?.totalPrice}",
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
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
                    selectedShippingId?.let {
                        Text(
                            fontFamily = poppinsFontFamily,
                            text = "$${shippingItem[selectedShippingId].price}",
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    } ?: run {
                        Text(
                            fontFamily = poppinsFontFamily,
                            text = "$0.00",
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        fontFamily = poppinsFontFamily,
                        text = "Final Price",
                        color = MaterialTheme.colorScheme.outline
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (selectedCouponId != null) {
                            Text(
                                text = if (selectedShippingId == null) "$${(state?.totalPrice)}"
                                else "$${(state?.totalPrice)?.plus(shippingItem[selectedShippingId].price)}",
                                fontFamily = poppinsFontFamily,
                                textDecoration = TextDecoration.LineThrough,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            fontFamily = poppinsFontFamily,
                            text = "$${"%.2f".format(finalPrice)}",
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Button(
                    enabled = isButtonEnabled,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                        .height(55.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        fontFamily = poppinsFontFamily,
                        text = "Choose Payment",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun CartItemDialog(
    items: List<Cart>,
    onDismiss: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = { onDismiss() },
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Your Order",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        },
        text = {
            LazyColumn(
                modifier = Modifier.height(364.dp)
            ){
                items(items = items) {
                    CartItemMini(
                        productName = it.productName,
                        price = it.productPrice,
                        orderCount = it.productQuantity,
                        imageId = it.productImage,
                        totalOrder = 0,
                        onDetailOrder = {}
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(
                    text = "OK",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetCoupon(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    state: List<Coupon>,
    selectedCouponId: Int?,
    onChoose: (Int) -> Unit,
    onConfirmationCoupon: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        windowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        BottomSheetCouponContent(
            modifier = Modifier
                .navigationBarsPadding()
                .wrapContentHeight(),
            state = state,
            selectedCouponId = selectedCouponId,
            onChoose = onChoose,
            onConfirmationCoupon = onConfirmationCoupon
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetShipping(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    state: List<Shipping>,
    selectedShippingId: Int?,
    onChoose: (Int) -> Unit,
    onConfirmationShipping: () -> Unit
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.background,
        windowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        BottomSheetShippingContent(
            modifier = Modifier
                .navigationBarsPadding()
                .wrapContentHeight(),
            onChoose = onChoose,
            selectedShippingId = selectedShippingId,
            state = state,
            onConfirmationShipping = onConfirmationShipping
        )
    }
}

@Composable
fun BottomSheetCouponContent(
    modifier: Modifier = Modifier,
    state: List<Coupon>,
    selectedCouponId: Int?,
    onChoose: (Int) -> Unit,
    onConfirmationCoupon: () -> Unit
) {
    var temporarySelectedCouponId by rememberSaveable { mutableStateOf(selectedCouponId) }

    Box(modifier = modifier
        .background(
            color = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp
                )
                .background(MaterialTheme.colorScheme.background)
                .align(Alignment.BottomCenter)
        ) {
            LazyColumn {
                itemsIndexed(items = state) { index, coupon ->
                    CouponItem(
                        isChoose = temporarySelectedCouponId == index,
                        onChoose = { temporarySelectedCouponId = index },
                        discount = coupon.discountedPrice,
                        description = coupon.description,
                        color1 = coupon.color1,
                        color2 = coupon.color2,
                        expiredDate = coupon.expiredDate
                    )
                }
            }
            Button(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .height(55.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    onChoose(temporarySelectedCouponId!!)
                    onConfirmationCoupon()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    fontFamily = poppinsFontFamily,
                    text = "Confirmation",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun BottomSheetShippingContent(
    modifier: Modifier = Modifier,
    state: List<Shipping>,
    selectedShippingId: Int?,
    onChoose: (Int) -> Unit,
    onConfirmationShipping: () -> Unit
) {
    var temporarySelectedShippingId by rememberSaveable { mutableStateOf(selectedShippingId) }

    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp
                )
                .background(MaterialTheme.colorScheme.background)
                .align(Alignment.BottomCenter)
        ) {
            LazyColumn {
                itemsIndexed(items = state) { index, shipping ->
                    ShippingItem(
                        isChoose = temporarySelectedShippingId == index,
                        onChoose = { temporarySelectedShippingId = index },
                        name = shipping.name,
                        price = shipping.price,
                        description = shipping.description
                    )
                }
            }
            Button(
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    onChoose(temporarySelectedShippingId!!)
                    onConfirmationShipping()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    fontFamily = poppinsFontFamily,
                    text = "Confirmation",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}

fun calculateFinalPrice(
    subTotal: Double,
    shippingPrice: Double?,
    coupon: Coupon?
) : Double {
    var finalPrice = subTotal

    val shippingCost = if (coupon?.discountedPrice == "FREE SHIPPING") {
        0.0
    } else {
        shippingPrice ?: 0.0
    }

    finalPrice += shippingCost

    coupon?.discountedPrice?.let { discount ->
        if (discount.endsWith("%")) {
            val discountPercentage = discount.replace("%","")
                .toDoubleOrNull() ?: 0.0
            finalPrice -= (finalPrice * (discountPercentage / 100))
        }
    }

    return finalPrice
}

@Preview(showBackground = true)
@Composable
private fun BottomSheetCouponContentPreview() {
    VibeStoreTheme(dynamicColor = false) {
        BottomSheetCouponContent(
            state = DataDummy.dummyCoupon,
            selectedCouponId = 0,
            onChoose = {},
            onConfirmationCoupon = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomSheetShippingContentPreview() {
    VibeStoreTheme {
        BottomSheetShippingContent(
            state = DataDummy.dummyShipping,
            selectedShippingId = 0,
            onChoose = {},
            onConfirmationShipping = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CartItemDialogPreview() {
    VibeStoreTheme(
        dynamicColor = false,
    ) {
        CartItemDialog(
            items = listOf(
                Cart(
                    productName = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    productImage = "",
                    productPrice = "78",
                    productQuantity = 2,
                    productId = 1,
                    productCategory = ""
                ),
                Cart(
                    productName = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    productImage = "",
                    productPrice = "78",
                    productQuantity = 2,
                    productId = 1,
                    productCategory = ""
                ),
                Cart(
                    productName = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    productImage = "",
                    productPrice = "78",
                    productQuantity = 2,
                    productId = 1,
                    productCategory = ""
                ),
                Cart(
                    productName = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    productImage = "",
                    productPrice = "78",
                    productQuantity = 2,
                    productId = 1,
                    productCategory = ""
                ),
                Cart(
                    productName = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    productImage = "",
                    productPrice = "78",
                    productQuantity = 2,
                    productId = 1,
                    productCategory = ""
                ),
                Cart(
                    productName = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    productImage = "",
                    productPrice = "78",
                    productQuantity = 2,
                    productId = 1,
                    productCategory = ""
                ),
                Cart(
                    productName = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    productImage = "",
                    productPrice = "78",
                    productQuantity = 2,
                    productId = 1,
                    productCategory = ""
                ),
            ),
            onDismiss = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun CheckoutContentPreview() {
    VibeStoreTheme(
        dynamicColor = false
    ) {
        CheckoutContent(
            onEditAddress = {},
            onShowDialog = {},
            onChooseShipping = {},
            selectedLocation = UserLocation(0, "", ""),
            selectedLocationId = -1,
            selectedShippingId = 2,
            shippingItem = DataDummy.dummyShipping,
            onChooseCoupon = {},
            selectedCouponId = 2,
            couponItem = DataDummy.dummyCoupon
        )
    }
}