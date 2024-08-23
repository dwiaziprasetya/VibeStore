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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vibestore.R
import com.example.vibestore.data.local.DataDummy
import com.example.vibestore.data.local.entity.Cart
import com.example.vibestore.data.local.entity.Order
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.model.PaymentMethod
import com.example.vibestore.ui.component.CardPaymentItem
import com.example.vibestore.ui.component.CartItemMini
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
    )
) {

    val latestOrder by viewModel.orderItems.observeAsState()
    var showDialog by remember { mutableStateOf(false) }
    val paymentMethod = DataDummy.dummyPaymentMethod
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    if (isSheetOpen) {
        BottomSheetShipping(
            onDismiss = { isSheetOpen = false }
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
            paymentMethod = paymentMethod,
            onChooseShipping = { isSheetOpen = true }
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
    onShowDialog: () -> Unit,
    paymentMethod: List<PaymentMethod>,
    onChooseShipping: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .weight(1f)
        ) {
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
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "Dwi Azi Prasetya",
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Jl. Durian No. 123, Banyubiru",
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                        )
                        Text(
                            text = "Kab. Semarang, Jawa Tengah",
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                        )
                        Text(
                            text = "Indonesia, 50123",
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable { onEditAddress() }
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
            Text(
                modifier = Modifier.padding(
                    top = 32.dp
                ),
                text = "Payment Methods",
                fontSize = 14.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold
            )
            paymentMethod.forEach { item ->
                CardPaymentItem(item = item)
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
                        text = "$5678.00",
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
                    text = "$1234"
                )
                Button(
                    modifier = Modifier
                        .height(55.dp)
                        .width(170.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        fontFamily = poppinsFontFamily,
                        text = "Pay Now",
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
fun BottomSheetShipping(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
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
                .wrapContentHeight()
        )
    }
}

@Composable
fun BottomSheetShippingContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 32.dp
                )
                .background(MaterialTheme.colorScheme.background)
                .align(Alignment.BottomCenter)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "REG ($13.00)",
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                        )
                        Text(
                            text = "Estimated time of arrival 2 - 3 days",
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                        )

                    }
                    RadioButton(
                        selected = false,
                        onClick = {}
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "REG ($13.00)",
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                        )
                        Text(
                            text = "Estimated time of arrival 2 - 3 days",
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                        )

                    }
                    RadioButton(
                        selected = false,
                        onClick = {}
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "REG ($13.00)",
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                        )
                        Text(
                            text = "Estimated time of arrival 2 - 3 days",
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                        )

                    }
                    RadioButton(
                        selected = false,
                        onClick = {}
                    )
                }
            }
            Button(
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = {},
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

@Preview(showBackground = true)
@Composable
private fun BottomSheetShippingContentPreview() {
    VibeStoreTheme {
        BottomSheetShippingContent()
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
            paymentMethod = DataDummy.dummyPaymentMethod,
            onChooseShipping = {}
        )
    }
}