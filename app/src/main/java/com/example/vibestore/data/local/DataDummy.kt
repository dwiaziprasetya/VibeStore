package com.example.vibestore.data.local

import com.example.vibestore.R
import com.example.vibestore.data.local.entity.UserLocation
import com.example.vibestore.model.PaymentMethod

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
}