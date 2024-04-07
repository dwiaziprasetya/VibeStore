package com.example.vibestore.model

data class ProductCategory(
    val nameCategory: String
)

val nameCategory = listOf(
    ProductCategory("All"),
    ProductCategory("Men"),
    ProductCategory("Woman"),
    ProductCategory("Electronics"),
    ProductCategory("Jewelery"),
)