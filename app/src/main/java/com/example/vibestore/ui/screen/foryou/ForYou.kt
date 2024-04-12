package com.example.vibestore.ui.screen.foryou

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import com.example.vibestore.ui.component.ProductCard

@Composable
fun ForYou() {
    LazyRow{
        items(6){
            ProductCard(
                image = "https://images.footballfanatics.com/manchester-united/manchester-united-adidas-home-authentic-shirt-2023-24_ss5_p-13384941+u-urcxn6bi50iq6jiewn9d+v-6luamd4m4wjyksnexulw.jpg?_hv=2&w=600",
                title = "Manchester United Jersey",
                price = 450
            )
        }
    }
}