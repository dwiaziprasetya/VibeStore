package com.example.vibestore.repository

import com.example.vibestore.data.remote.retrofit.ApiConfig
import com.example.vibestore.model.ProductResponseItem

class ProductRepository {
    private val productService = ApiConfig.getApiService()

    suspend fun getAllProducts(): List<ProductResponseItem> {
        return productService.getAllProduct()
    }
}