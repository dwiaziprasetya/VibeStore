package com.example.vibestore.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vibestore.data.local.DataDummy
import com.example.vibestore.model.PaymentMethod
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun CardPaymentItem(
    modifier: Modifier = Modifier,
    item: PaymentMethod
) {
    Card(
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = "",
                tint = Color.Unspecified,
            )
            Spacer(modifier = Modifier.weight(1f))
            RadioButton(
                selected = false,
                onClick = {  }
            )
        }
    }
}

@Preview
@Composable
private fun CardPaymentItemPreview() {
    VibeStoreTheme(dynamicColor = false) {
        CardPaymentItem(
            item = DataDummy.dummyPaymentMethod.first()
        )
    }
}