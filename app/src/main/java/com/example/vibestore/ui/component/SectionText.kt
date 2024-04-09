package com.example.vibestore.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt

@Composable
fun SectionText(
    text: String
) {
    Row(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Text(
            text = text,
            modifier = Modifier
                .weight(1f),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            text = "See all",
            color = Color("#29bf12".toColorInt())
        )
    }
}