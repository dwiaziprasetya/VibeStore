package com.example.vibestore.ui.screen.address.add_address

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.vibestore.R
import com.example.vibestore.helper.DialogHelper
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.common.UiState
import com.example.vibestore.ui.component.AnimatedShimmerDetailAddress
import com.example.vibestore.ui.navigation.model.Screen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("MissingPermission")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddAddressScreen(
    viewModel: AddAddressViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current
        )
    ),
    navController: NavController
) {
    val initialPosition = LatLng(-7.051663448625907, 110.44415489433972)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialPosition, 17f)
    }

    var name by remember { mutableStateOf("") }
    val scaffoldState = rememberBottomSheetScaffoldState()
    val context = LocalContext.current
    val uiState by viewModel.uiState.observeAsState(UiState.Loading)
    val locationName by viewModel.locationName.observeAsState("")
    var hasZoomedManually by remember { mutableStateOf(false) }

    var hasLocationPermissions by remember { mutableStateOf(false) }

    LaunchedEffect(uiState) {
        if (uiState is UiState.Success && !hasZoomedManually) {
            val location = (uiState as UiState.Success<LatLng>).data
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(location, 17f)
            )
        }
    }

    RequestLocationPermission {
        hasLocationPermissions = true
    }

    LaunchedEffect(Unit) {
        scaffoldState.bottomSheetState.expand()
    }

    LaunchedEffect(cameraPositionState.isMoving) {
        if (!cameraPositionState.isMoving) {
            val currentLatLng = cameraPositionState.position.target
            viewModel.getInitialLocationName(currentLatLng)
        } else {
            hasZoomedManually = true
        }
    }

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp
        ),
        sheetPeekHeight = 0.dp,
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .size(40.dp)
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(50)
                        )
                        .padding(8.dp)
                        .clickable { navController.navigateUp() }
                )

                Icon(
                    imageVector = Icons.Default.MyLocation,
                    contentDescription = "Get Your Location",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(40.dp)
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(50)
                        )
                        .padding(8.dp)
                        .clickable {
                            if (hasLocationPermissions) {
                                hasZoomedManually = false
                                viewModel.getCurrentLocation()
                            } else {
                                Toast
                                    .makeText(
                                        context,
                                        "Location permission denied",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                )
            }
        },
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheetContent(
                locationName = locationName,
                uiState = uiState,
                viewModel = viewModel,
                onRecipientNameChange = { name = it },
                cameraPositionState = cameraPositionState,
                recipientName = name,
                onConfirm = {
                    viewModel.addUsersLocation(
                        name,
                        locationName,
                    )
                    DialogHelper.showDialogSuccess(
                        context = context,
                        title = "Success",
                        textContent = "Location added",
                        textConfirm = "OK",
                        onConfirm = {
                            navController.navigate(Screen.Address.route){
                                popUpTo(Screen.Address.route) { inclusive = true }
                            }
                        }
                    )
                }
            )
        },
        modifier = Modifier.navigationBarsPadding(),
        sheetGesturesEnabled = true,
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            GoogleMapContent(
                cameraPositionState = cameraPositionState,
            )
        }
    }
}

@Composable
fun GoogleMapContent(
    cameraPositionState: CameraPositionState,
) {
    Box(modifier = Modifier
        .fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color.White,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_circle_dot_filled),
                    contentDescription = "Custom Pin",
                    modifier = Modifier
                        .size(40.dp),
                    tint = Color("#FF9100".toColorInt())
                )
            }

            Box(
                modifier = Modifier
                    .size(10.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = 15.dp)
                    .background(
                        color = Color("#FF9100".toColorInt()),
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun BottomSheetContent(
    locationName: String,
    uiState: UiState<LatLng>,
    viewModel: AddAddressViewModel,
    cameraPositionState: CameraPositionState,
    recipientName: String,
    onRecipientNameChange: (String) -> Unit,
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .height(265.dp)
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 8.dp
            )
    ) {
        when (uiState) {
            is UiState.Loading -> {
                Text(
                    text = "Recipient Name",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                BasicTextField(
                    value = recipientName,
                    onValueChange = onRecipientNameChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .drawBehind {
                            val strokeWidth = 2.dp.toPx()
                            val y = size.height - strokeWidth / 2
                            drawLine(
                                color = Color(0xFFCAC8C8),
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        },
                    textStyle = TextStyle(
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                    ),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                        ) {
                            if (recipientName.isEmpty()) {
                                Text(
                                    text = "Enter recipient's name",
                                    style = TextStyle(
                                        fontFamily = poppinsFontFamily,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Gray
                                    )
                                )
                            }
                            innerTextField()
                        }
                    }
                )
                Text(
                    text = "Detail Address",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row {
                    Icon(
                        painter = painterResource(R.drawable.icon_circle_dot_filled),
                        contentDescription = "",
                        tint = Color("#FF9100".toColorInt()),
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Box(modifier = Modifier.fillMaxSize()){
                        AnimatedShimmerDetailAddress()

                        val currentLatLng = cameraPositionState.position.target
                        viewModel.getInitialLocationName(currentLatLng)
                    }
                }
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(55.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    androidx.compose.material3.Text(
                        fontFamily = poppinsFontFamily,
                        text = "Confirmation",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            } is UiState.Success -> {
                Text(
                    text = "Recipient Name",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                BasicTextField(
                    value = recipientName,
                    onValueChange = onRecipientNameChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .drawBehind {
                            val strokeWidth = 2.dp.toPx()
                            val y = size.height - strokeWidth / 2
                            drawLine(
                                color = Color(0xFFCAC8C8),
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        },
                    textStyle = TextStyle(
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                    ),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                        ) {
                            if (recipientName.isEmpty()) {
                                Text(
                                    text = "Enter recipient's name",
                                    style = TextStyle(
                                        fontFamily = poppinsFontFamily,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Gray
                                    )
                                )
                            }
                            innerTextField()
                        }
                    }
                )
                Text(
                    text = "Detail Address",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row {
                    Icon(
                        painter = painterResource(R.drawable.icon_circle_dot_filled),
                        contentDescription = "",
                        tint = Color("#FF9100".toColorInt()),
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Text(
                        modifier = Modifier.height(60.dp),
                        text = locationName,
                        maxLines = 3,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                    )
                }
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(55.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    androidx.compose.material3.Text(
                        fontFamily = poppinsFontFamily,
                        text = "Confirmation",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
            is UiState.Error -> {
                Text(
                    text = "Error: ${uiState.errorMessage}",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun RequestLocationPermission(onPermissionGranted: () -> Unit) {
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
            val coarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

            if (fineLocationGranted || coarseLocationGranted) {
                onPermissionGranted()
            } else {
                Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    )

    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun AddAddressScreenPreview() {
    VibeStoreTheme {
        GoogleMapContent(
            cameraPositionState = rememberCameraPositionState()
        )
    }
}