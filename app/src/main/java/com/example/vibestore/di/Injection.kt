package com.example.vibestore.di

import android.content.Context
import com.example.vibestore.SessionPreferences
import com.example.vibestore.data.remote.retrofit.ApiConfig
import com.example.vibestore.dataStore
import com.example.vibestore.repository.VibeStoreRepository

object Injection {
    fun provideRepository(context: Context): VibeStoreRepository {
        val user = SessionPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return VibeStoreRepository.getInstance(apiService, user)
    }
}