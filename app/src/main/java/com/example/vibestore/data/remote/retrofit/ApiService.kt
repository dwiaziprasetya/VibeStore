package com.example.vibestore.data.remote.retrofit

import com.example.vibestore.model.ProductResponseItem
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    fun getAllProduct(): retrofit2.Call<List<ProductResponseItem>>
}