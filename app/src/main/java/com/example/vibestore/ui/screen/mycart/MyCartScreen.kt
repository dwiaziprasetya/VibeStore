package com.example.vibestore.ui.screen.mycart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.R
import com.example.vibestore.ui.component.CartItem
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCartScreen(
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
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
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LazyColumn {
                items(10){
                    CartItem()
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(85.dp)
                    .background(Color.White)
            ){
                Divider()
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .weight(
                                1f
                            ),
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 40.sp,
                        text = "$198.5"
                    )
                    Button(
                        modifier = Modifier
                            .height(55.dp)
                            .width(170.dp),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color("#29bf12".toColorInt())
                        )
                    ) {
                        Text(
                            fontFamily = poppinsFontFamily,
                            text = stringResource(R.string.checkout),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4_XL
)
@Composable
private fun MyCartScreenPreview() {
    VibeStoreTheme {
        MyCartScreen(
            rememberNavController()
        )
    }
}