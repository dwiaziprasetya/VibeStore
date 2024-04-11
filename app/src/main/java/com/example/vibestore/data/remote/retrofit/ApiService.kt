package com.example.vibestore.data.remote.retrofit

import com.example.vibestore.model.ProductResponseItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getAllProduct(): List<ProductResponseItem>

    @GET("products/category/{category}")
    suspend fun getProductByCategory(
        @Path("category")
        category: String
    ): List<ProductResponseItem>
}