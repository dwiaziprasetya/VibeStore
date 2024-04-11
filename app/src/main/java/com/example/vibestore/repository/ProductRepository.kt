package com.example.vibestore.repository

import com.example.vibestore.data.remote.retrofit.ApiConfig
import com.example.vibestore.model.ProductResponseItem

class ProductRepository {
    private val getApiService = ApiConfig.getApiService()

    suspend fun getAllProduct(): List<ProductResponseItem> {
        return getApiService.getAllProduct()
    }

    suspend fun getProductByCategory(category: String): List<ProductResponseItem> {
        return getApiService.getProductByCategory(category)
    }
}