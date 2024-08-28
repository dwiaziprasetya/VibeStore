package com.example.vibestore.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun CouponItem(
    modifier: Modifier = Modifier,
    onChoose: () -> Unit,
    isChoose: Boolean,
    discount: String,
    description: String,
    expiredDate: String,
    color1: Color,
    color2: Color
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onChoose() }
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
                    text = discount,
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = description,
                    fontSize = 12.sp,
                    fontFamily = poppinsFontFamily,
                )

            }
            RadioButton(
                selected = isChoose,
                onClick = onChoose
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CouponItemPreview() {
    VibeStoreTheme {
        CouponItem(
            onChoose = {},
            isChoose = false,
            discount = "25%",
            description = "Applies to get 25% off",
            expiredDate = "31 Desember 2024",
            color1 = Color(0xFFFFA726),
            color2 = Color(0xFFFFD54F)
        )
    }
}