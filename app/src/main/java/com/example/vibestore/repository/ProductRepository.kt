package com.example.vibestore.repository

import com.example.vibestore.data.remote.retrofit.ApiService
import com.example.vibestore.model.ProductResponseItem

class ProductRepository private constructor(
    private val apiService : ApiService
){

    suspend fun getAllProducts(limit: Int): List<ProductResponseItem> {
        return apiService.getAllProduct(limit)
    }

    suspend fun sortProduct(sort: String): List<ProductResponseItem> {
        return apiService.sortProduct(sort)
    }

    suspend fun getProductByCategory(category: String, limit: Int): List<ProductResponseItem> {
        return apiService.getProductByCategory(category, limit)
    }

    suspend fun getSingleProduct(id: Int): ProductResponseItem {
        return apiService.getSingleProduct(id)
    }

    suspend fun searchProduct(query: String): List<ProductResponseItem> {
        val allproduct = apiService.getAllProduct(Int.MAX_VALUE)
        return allproduct.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    companion object {
        fun getInstance(
            apiService: ApiService
        ) = ProductRepository(apiService)
    }
}