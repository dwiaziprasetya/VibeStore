package com.example.vibestore.di

import android.content.Context
import com.example.vibestore.data.local.database.VibeStoreRoomDatabase
import com.example.vibestore.data.remote.retrofit.ApiConfig
import com.example.vibestore.data.repository.VibeStoreRepository
import com.example.vibestore.util.SessionPreferences
import com.example.vibestore.util.dataStore

object Injection {
    fun provideRepository(context: Context): VibeStoreRepository {
        val user = SessionPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        val cartDao = VibeStoreRoomDatabase.getDatabase(context).cartDao()
        val favoriteDao = VibeStoreRoomDatabase.getDatabase(context).favouriteDao()
        return VibeStoreRepository.getInstance(apiService, user, cartDao, favoriteDao)
    }
}