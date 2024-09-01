package com.example.vibestore.ui.screen.notification

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import com.example.vibestore.ui.component.NotificationItem
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    navController: NavController,
    viewModel: NotificationViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current,
        )
    )
) {
    val notification by viewModel.notifications.observeAsState(emptyList())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .statusBarsPadding(),
                title = {
                    Text(
                        text = stringResource(R.string.notification),
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
        Box(modifier = Modifier.padding(innerPadding)) {
            if (notification.isNotEmpty()) {
                LazyColumn {
                    items(items = notification) { notif ->
                        NotificationItem(
                            notificationType = notif.notificationType,
                            date = notif.date,
                            message = notif.message,
                            firstProductImage = notif.firstProductImage,
                            firstProductName = notif.firstProductName,
                            quantityCheckout = notif.quantityCheckout,
                            messageDetail = notif.messageDetail,
                            isRead = notif.isRead,
                            onNotificationClick = {
                                viewModel.markAsRead(notif.id)
                            }
                        )
                    }
                }
            } else {
                Text("empty")
            }
        }
    }
}

@Preview
@Composable
private fun NotificationScreenPreview() {
    VibeStoreTheme(
        dynamicColor = false
    ) {
        NotificationScreen(
            navController = rememberNavController()
        )
    }
}