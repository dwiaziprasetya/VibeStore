package com.example.vibestore.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestore.R
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun ProductCard() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .width(160.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(R.drawable.samplemodel),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Text(
            text = "Moth Button Down",
            fontSize = 12.sp,
            modifier = Modifier
        )
        Text(
            text = "$140.00",
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductCardPreview() {
    VibeStoreTheme {
        ProductCard()
    }
}