package com.example.vibestore.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.vibestore.R
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun Search(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    TextField(
        value = searchQuery,
        onValueChange = { text ->
            searchQuery = text
            onSearch(text)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.icon_search),
                contentDescription = "Search",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .scale(1f),
                tint = Color.Black,
            )
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
        ),
        placeholder = {
            Text(
                text = "Search Items",
                fontFamily = poppinsFontFamily,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier
                    .offset(y = (-1.4).dp)
            )
        },
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color("#D1D5DB".toColorInt()),
                shape = RoundedCornerShape(30.dp)
            )
            .fillMaxWidth()
            .height(50.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchPreview() {
    VibeStoreTheme(dynamicColor = false) {
        Search(
            modifier = Modifier
                .padding(16.dp)
        )
    }
}