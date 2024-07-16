package com.example.vibestore.ui.screen.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.vibestore.R
import com.example.vibestore.helper.DialogHelper
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.navigation.Screen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current
        )
    ),
    navController: NavHostController
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val username by viewModel.getUsername().observeAsState()
    var dialog by remember { mutableStateOf<SweetAlertDialog?>(null) }

    Scaffold(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row {
                        Text(
                            text = "Your Profile",
                            fontFamily = poppinsFontFamily,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Image(
                            painter = painterResource(R.drawable.logo),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(30.dp)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.image_user),
                    contentDescription = "profile",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .border(
                            width = 2.dp,
                            color = Color("#29bf12".toColorInt()),
                            shape = CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        letterSpacing = 0.001.sp,
                        text = username.toString(),
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,

                    )
                    Text(
                        text = "$ 2.341.000",
                        fontFamily = poppinsFontFamily,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier
                    )
                }
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "settings",
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
            Divider(color = Color(0xFFE3E3E3))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    tint = Color("#f8b62d".toColorInt()),
                    imageVector = Icons.Default.ShoppingBag,
                    contentDescription = "bag",
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .size(20.dp)
                )
                Text(
                    text = "My Orders",
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f),
                    fontSize = 14.sp,
                )
                Icon(
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "forward"
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    tint = Color("#00dae3".toColorInt()),
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "location",
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .size(20.dp)
                )
                Text(
                    text = "Address",
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f),
                    fontSize = 14.sp,
                )
                Icon(
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "forward"
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    tint = Color("#374d7c".toColorInt()),
                    imageVector = Icons.Default.Lock,
                    contentDescription = "lock",
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .size(20.dp)
                )
                Text(
                    text = "Change Password",
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f),
                    fontSize = 14.sp,
                )
                Icon(
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "forward"
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    tint = Color("#082db4".toColorInt()),
                    imageVector = Icons.AutoMirrored.Filled.Help,
                    contentDescription = "help",
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .size(20.dp)
                )
                Text(
                    text = "Help & Support",
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f),
                    fontSize = 14.sp,
                )
                Icon(
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "forward"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        dialog = DialogHelper.showDialogCustom(
                            context = context,
                            title = "Log out",
                            textContent = "Are you sure you want to log out?",
                            onDismis = { dialog?.dismissWithAnimation() },
                            onConfirm = {
                                dialog?.dismissWithAnimation()
                                dialog = DialogHelper.showDialogLoading(
                                    context = context,
                                    textContent = "Please wait"
                                )
                                scope.launch {
                                    delay(2000)
                                    dialog?.dismissWithAnimation()
                                    navController.popBackStack(Screen.MainNav.route, true)
                                    navController.navigate(Screen.AuthNav.route)
                                    Toast
                                        .makeText(
                                            context,
                                            "Log out success",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                    viewModel.logout()
                                }
                            }
                        )
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = "log out",
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .size(20.dp),
                    tint = Color("#bf122f".toColorInt())
                )
                Text(
                    text = "Log out",
                    fontFamily = poppinsFontFamily,
                    color = Color("#bf122f".toColorInt()),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4_XL
)
@Composable
private fun ProfileScreenPreview() {
    VibeStoreTheme {
        ProfileScreen(
            navController = rememberNavController()
        )
    }
}