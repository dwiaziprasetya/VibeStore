package com.example.vibestore.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun ProductCounter(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .size(width = 110.dp, height = 40.dp)
            .padding(4.dp)
            .border(
                border = BorderStroke(
                    1.dp,
                    Color("#e3e3e3".toColorInt())
                ),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = "—",
                fontSize = 22.sp,
                color = Color("#e3e3e3".toColorInt()),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
            )
        }
        Text(
            fontFamily = poppinsFontFamily,
            text = "1",
            modifier = Modifier
                .weight(1f)
                .testTag("count"),
            fontSize = 23.sp,
            textAlign = TextAlign.Center,
        )
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            color = Color.Transparent,
            contentColor = Color("#29bf12".toColorInt()),
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = "＋",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductCounterPreview() {
    VibeStoreTheme {
        ProductCounter()
    }
}