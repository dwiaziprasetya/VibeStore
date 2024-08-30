package com.example.vibestore.ui.screen.payment

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vibestore.data.local.DataDummy
import com.example.vibestore.data.local.entity.Checkout
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.model.PaymentMethod
import com.example.vibestore.ui.component.CardPaymentItem
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: PaymentViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current,
        )
    )
) {
    val latestCeckout by viewModel.latestCheckout.observeAsState()
    val paymentMethod = DataDummy.dummyPaymentMethod
    val selectedPaymentId by viewModel.selectedPaymentId.observeAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .statusBarsPadding(),
                title = {
                    Text(
                        text = "Payment",
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
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            PaymentContent(
                state = paymentMethod,
                selectedPaymentId = selectedPaymentId,
                latestCheckout = latestCeckout,
                isButtonEnabled = selectedPaymentId != null,
                onChoosePaymentMethod = {
                    viewModel.addPaymentMethodToCheckout(
                        paymentMethod[selectedPaymentId!!].name
                    )
                },
                onChooseCardPayment = {
                    viewModel.selectPayment(it)
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PaymentContent(
    modifier: Modifier = Modifier,
    state: List<PaymentMethod>,
    latestCheckout: Checkout?,
    isButtonEnabled: Boolean = false,
    onChoosePaymentMethod: () -> Unit,
    selectedPaymentId: Int? = null,
    onChooseCardPayment: (Int) -> Unit
) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                )
                .fillMaxSize()
                .weight(1f)
        ) {
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
            ) {
                itemsIndexed(items = state){ index, payment ->
                    androidx.compose.animation.AnimatedVisibility(
                        modifier = Modifier.animateItemPlacement(tween(100)),
                        visible = visible,
                        enter = slideInVertically(
                            initialOffsetY = { -it },
                            animationSpec = tween(durationMillis = 300)
                        ) + fadeIn(animationSpec = tween(durationMillis = 300))
                    ) {
                        CardPaymentItem(
                            icon = payment.icon,
                            name = payment.name,
                            isChoose = selectedPaymentId == index,
                            onChoose = {
                                onChooseCardPayment(index)
                            }
                        )
                    }
                }
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
                        text = "Final Price",
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        fontFamily = poppinsFontFamily,
                        text = "$${"%.2f".format(latestCheckout?.totalPrice)}",
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
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
                    onClick = onChoosePaymentMethod,
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

@Preview(showBackground = true)
@Composable
private fun PaymentScreenPreview() {
    VibeStoreTheme(dynamicColor = false) {
        PaymentContent(
            state = DataDummy.dummyPaymentMethod,
            latestCheckout = Checkout(
                id = 0,
                receiverName = "",
                receiverAddress = "",
                orderItems = emptyList(),
                shippingMethod = "",
                shippingCost = 0.0,
                shippingDescription = "",
                paymentMethod = "",
                totalPrice = 0.0,
            ),
            onChoosePaymentMethod = {},
            onChooseCardPayment = {}
        )
    }
}