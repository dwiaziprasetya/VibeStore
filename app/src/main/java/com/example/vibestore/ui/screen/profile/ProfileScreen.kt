package com.example.vibestore.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LockOpen
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.vibestore.R
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
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
                    imageVector = Icons.Default.AccountCircle,
                    colorFilter = ColorFilter.tint(Color.Gray),
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
                        text = "Dwi Azi Prasetya",
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
                    tint = MaterialTheme.colorScheme.outline,
                    imageVector = Icons.Default.ShoppingBag,
                    contentDescription = "bag",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(20.dp)
                )
                Text(
                    text = "My Orders",
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier.weight(1f),
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
                    tint = MaterialTheme.colorScheme.outline,
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "location",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(20.dp)
                )
                Text(
                    text = "Address",
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier.weight(1f),
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
                    tint = MaterialTheme.colorScheme.outline,
                    imageVector = Icons.Default.LockOpen,
                    contentDescription = "lock",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(20.dp)
                )
                Text(
                    text = "Change Password",
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier.weight(1f),
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
                    tint = MaterialTheme.colorScheme.outline,
                    imageVector = Icons.AutoMirrored.Filled.HelpOutline,
                    contentDescription = "help",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(20.dp)
                )
                Text(
                    text = "Help & Support",
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier.weight(1f),
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
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = "log out",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(20.dp),
                    tint = Color("#bf122f".toColorInt())
                )
                Text(
                    text = "Log out",
                    fontFamily = poppinsFontFamily,
                    color = Color("#bf122f".toColorInt()),
                    fontSize = 14.sp,
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
        ProfileScreen()
    }
}