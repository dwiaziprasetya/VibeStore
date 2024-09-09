package com.example.vibestore.ui.screen.address

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.R
import com.example.vibestore.data.local.DataDummy
import com.example.vibestore.data.local.entity.UserLocation
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.component.AddressItemScreen2
import com.example.vibestore.ui.navigation.model.Screen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(
    navHostController: NavHostController,
    viewModel: AddressViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current
        )
    )
) {
    val scope = rememberCoroutineScope()
    val allUserLocation by viewModel.allUserLocations.observeAsState()
    val selectedItemId by viewModel.selectedItemId.observeAsState()

    Scaffold(
        topBar = {
           CenterAlignedTopAppBar(
               modifier = Modifier
                   .padding(horizontal = 16.dp)
                   .statusBarsPadding(),
               title = {
                   Text(
                       text = stringResource(R.string.address),
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
        AddressContent(
            modifier = Modifier.padding(innerPadding),
            state = allUserLocation ?: emptyList(),
            navHostController = navHostController,
            selectedItemId = selectedItemId,
            onSelectedItem = { id -> viewModel.selectItem(id) },
            onDeletedItem = { id -> viewModel.deleteItem(id) },
            scope = scope,
            onConfirmationClick = {
                selectedItemId.let { id -> 
                    navHostController.navigate(
                        Screen.Checkout.createRoute(id ?: 0)
                    ) {
                        popUpTo(Screen.Checkout.route) { inclusive = true }
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddressContent(
    modifier: Modifier = Modifier,
    state: List<UserLocation>,
    scope: CoroutineScope,
    selectedItemId: Int?,
    onSelectedItem: (Int) -> Unit,
    onDeletedItem: (Int) -> Unit,
    onConfirmationClick: () -> Unit,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            if (state.isEmpty()) {
                Box(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .height(200.dp),
                ) {
                    Text(
                        text = "No Address Found",
                        modifier = Modifier.align(Alignment.Center),
                        fontFamily = poppinsFontFamily,
                    )
                }
            } else {
                LazyColumn {
                    items(items = state, key = { it.id }) { userLocation ->
                        AddressItemScreen2(
                            name = userLocation.name,
                            address = userLocation.address,
                            isSelected = selectedItemId == userLocation.id,
                            onChooseClick = { onSelectedItem(userLocation.id) },
                            onDeleteClick = { onDeletedItem(userLocation.id) },
                            modifier = Modifier.animateItemPlacement(tween(100))
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }
            }
        }

        Column(modifier = Modifier.padding(top = 16.dp)) {
            Card(
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.LightGray
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch {
                                navHostController.navigate(Screen.AddAddress.route)
                            }
                        }
                ) {
                    Text(
                        text = "Add New Address",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppinsFontFamily,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            Button(
                enabled = selectedItemId != null,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = onConfirmationClick,
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
private fun AddressContentPreview() {
    VibeStoreTheme(dynamicColor = false) {
        AddressContent(
            state = DataDummy.dummyUserLocation,
            navHostController = rememberNavController(),
            scope = rememberCoroutineScope(),
            selectedItemId = 2,
            onSelectedItem = {},
            onDeletedItem = {},
            onConfirmationClick = {}
        )
    }
}