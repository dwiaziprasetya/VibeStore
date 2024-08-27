package com.example.vibestore.data.local

import androidx.compose.ui.graphics.Color
import com.example.vibestore.R
import com.example.vibestore.data.local.entity.UserLocation
import com.example.vibestore.model.Coupon
import com.example.vibestore.model.PaymentMethod
import com.example.vibestore.model.Shipping

object DataDummy {
    val dummyUserLocation = listOf(
        UserLocation(
            id = 1,
            name = "Mas Azi",
            address = "Jl. Durian No. 123, Banyubiru " +
                    "Kab. Semarang, Jawa Tengah " +
                    "Indonesia 50664"
        ),
        UserLocation(
            id = 2,
            name = "Haryanto",
            address = "Jl. Durian No. 123, Banyubiru " +
                    "Kab. Semarang, Jawa Tengah " +
                    "Indonesia 50664"
        ),
    )
    val dummyShipping = listOf(
        Shipping(
            name = "REG",
            price = 13.00,
            description = "Estimated time of arrival 2 - 3 days"
        ),
        Shipping(
            name = "OKE",
            price = 15.00,
            description = "Estimated time of arrival 1 - 2 days"
        ),
        Shipping(
            name = "YES",
            price = 10.00,
            description = "Estimated time of arrival 1 - 2 days"
        )
    )
    val dummyPaymentMethod = listOf(
        PaymentMethod(
            icon = R.drawable.icon_alfamart,
            name = "Alfamart"
        ),
        PaymentMethod(
            icon = R.drawable.icon_brimo,
            name = "BRI Mobile"
        )
    )
    val dummyCoupon = listOf(
        Coupon(
            discountedPrice = "FREE SHIPPING",
            description = "Applies to get free shipping",
            expiredDate = "31 Desember 2024",
            color1 = Color(0xFF9733EE),
            color2 = Color(0xFFDA22FF)
        ),
        Coupon(
            discountedPrice = "25%",
            description = "Applies to get 25% off",
            expiredDate = "31 Desember 2024",
            color1 = Color(0xFFFFA726),
            color2 = Color(0xFFFFD54F)
        ),
        Coupon(
            discountedPrice = "50%",
            description = "Applies to get 50% off",
            expiredDate = "31 Desember 2024",
            color1 = Color(0xFF00C9FF),
            color2 = Color(0xFF92FE9D)
        )
    )
}