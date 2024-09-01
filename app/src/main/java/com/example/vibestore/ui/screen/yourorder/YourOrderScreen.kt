package com.example.vibestore.ui.screen.yourorder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.R
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.component.BoxEmptyAnimation
import com.example.vibestore.ui.component.OrderItem
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourOrderScreen(
    navController: NavController,
    viewModel: YourOrderViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current,
        )
    )
) {

    val checkoutItems by viewModel.checkoutItems.observeAsState(emptyList())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .statusBarsPadding(),
                title = {
                    Text(
                        text = stringResource(R.string.your_order),
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
                            .clickable { navController.navigateUp() }
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
                .background(MaterialTheme.colorScheme.background)
        ){
            if (checkoutItems.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    items(items = checkoutItems) { checkout ->
                        OrderItem(
                            date = checkout.formattedCheckoutDate,
                            firstOrderItemName = checkout.orderItems.firstOrNull()?.productName ?: "",
                            firstOrderItemImage = checkout.orderItems.firstOrNull()?.productImage ?: "",
                            otherOrderQuantity = checkout.orderItems.size - 1,
                            totalPrice = checkout.totalPrice
                        )
                    }
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    BoxEmptyAnimation(
                        modifier = Modifier.size(200.dp)
                    )
                    Text(
                        text = "No Order History",
                        fontFamily = poppinsFontFamily,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun YourOrderScreenPreview() {
    VibeStoreTheme(
        dynamicColor = false
    ) {
        YourOrderScreen(
            navController = rememberNavController()
        )
    }
}