package com.example.vibestore.di

import android.content.Context
import com.example.vibestore.data.remote.retrofit.ApiConfig
import com.example.vibestore.repository.ProductRepository

object Injection {
    fun provideRepository(context: Context): ProductRepository {
        val apiService = ApiConfig.getApiService()
        return ProductRepository.getInstance(apiService)
    }
}