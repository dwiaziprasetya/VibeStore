package com.example.vibestore.repository

import com.example.vibestore.util.SessionPreferences
import com.example.vibestore.data.remote.retrofit.ApiService
import com.example.vibestore.model.LoginResponse
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.model.UserResponse
import kotlinx.coroutines.flow.Flow

class VibeStoreRepository private constructor(
    private val apiService : ApiService,
    private val pref: SessionPreferences
){
    fun getSession() : Flow<LoginResponse> {
        return pref.getSession()
    }

    fun getUsername() : Flow<String> {
        return pref.getUsername()
    }

    suspend fun saveLoginData(username: String, token: String) {
        pref.saveLoginData(username, token)
    }

    suspend fun logout() {
        pref.logout()
    }

    suspend fun login(
        username: String,
        password: String
    ): LoginResponse {
        return apiService.login(username, password)
    }

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): UserResponse {
        return apiService.register(username, email, password)
    }

    suspend fun getAllProducts(limit: Int): List<ProductResponseItem> {
        return apiService.getAllProduct(limit)
    }

    suspend fun getProductByCategory(
        category: String,
        limit: Int
    ): List<ProductResponseItem> {
        return apiService.getProductByCategory(category, limit)
    }

    suspend fun getSingleProduct(id: Int): ProductResponseItem {
        return apiService.getSingleProduct(id)
    }

    suspend fun sortProduct(sort: String): List<ProductResponseItem> {
        return apiService.sortProduct(sort)
    }

    suspend fun searchProduct(query: String): List<ProductResponseItem> {
        val allproduct = apiService.getAllProduct(Int.MAX_VALUE)
        return allproduct.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    companion object {
        fun getInstance(
            apiService: ApiService,
            userPreferences: SessionPreferences
        ) = VibeStoreRepository(
            apiService,
            userPreferences
        )
    }
}