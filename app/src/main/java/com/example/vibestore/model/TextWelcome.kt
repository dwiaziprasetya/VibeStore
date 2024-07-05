package com.example.vibestore.model

data class TextWelcome(
    val text1: String,
    val text2: String
)

val dummyTextWelcome = listOf(
    TextWelcome(
        text1 = "Discovering your\n " +
                "fancy fashion style",
        text2 = "Find the perfect outfit for any occasion,\n" +
                "from casual wear to formal attire"
    ),TextWelcome(
        text1 = "Unlocking of\n " +
                "timeless elegance",
        text2 = "Explore a wide range of outfits\n" +
                "that transition from day to night"
    ),TextWelcome(
        text1 = "Embracing to\n " +
                "fashion trends",
        text2 = "Discover pieces that\n" +
                "and updated and stylish"
    ),
)