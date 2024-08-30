package com.example.vibestore.ui.screen.payment.success_payment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.ui.component.SuccessAnimation
import com.example.vibestore.ui.navigation.model.Screen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun SuccessPayment(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(
                    top = 32.dp,
                    start = 16.dp,
                    end = 16.dp
                )
                .align(Alignment.TopCenter)
        ) {
            SuccessAnimation(
                modifier = Modifier
                    .size(250.dp)
            )
            Text(
                modifier = Modifier
                    .offset(y = (-30).dp),
                fontSize = 24.sp,
                text = "Payment Success",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {
                Text(
                    fontFamily = poppinsFontFamily,
                    text = "$12.728.00",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Save 30%",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = poppinsFontFamily,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Gray,
                            offset = Offset(2f, 2f),
                            blurRadius = 4f
                        )
                    )
                )
            }
            Divider(
                color = Color(0xFFE3E3E3),
                modifier = Modifier.padding(
                    top = 16.dp,
                    bottom = 16.dp
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    fontFamily = poppinsFontFamily,
                    text = "No. Reference",
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "12345678910",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFontFamily,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    fontFamily = poppinsFontFamily,
                    text = "Shipping",
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "YES",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFontFamily,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    fontFamily = poppinsFontFamily,
                    text = "Total Order",
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "5",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFontFamily,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    fontFamily = poppinsFontFamily,
                    text = "Receiver",
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Dwi Azi Prasetya",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFontFamily,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    fontFamily = poppinsFontFamily,
                    text = "Payment Method",
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Paypal",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFontFamily,
                )
            }
        }
        Button(
            modifier = Modifier
                .padding(
                    top = 8.dp,
                    bottom = 8.dp,
                    start = 16.dp,
                    end = 16.dp
                )
                .height(55.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                navController.popBackStack(Screen.MainNav.route, true)
                navController.navigate(Screen.MainNav.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                fontFamily = poppinsFontFamily,
                text = "Back to Home",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SuccessPaymentPreview() {
    VibeStoreTheme(dynamicColor = false) {
        SuccessPayment(
            navController = rememberNavController()
        )
    }
}