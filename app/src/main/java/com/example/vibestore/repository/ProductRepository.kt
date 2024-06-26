package com.example.vibestore.repository

import com.example.vibestore.data.remote.retrofit.ApiConfig
import com.example.vibestore.model.ProductResponseItem

class ProductRepository {
    private val productService = ApiConfig.getApiService()

    suspend fun getAllProducts(limit: Int): List<ProductResponseItem> {
        return productService.getAllProduct(limit)
    }

    suspend fun getProductByCategory(category: String, limit: Int): List<ProductResponseItem> {
        return productService.getProductByCategory(category, limit)
    }

    suspend fun getSingleProduct(id: Int): ProductResponseItem {
        return productService.getSingleProduct(id)
    }
}