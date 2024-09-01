package com.example.vibestore.di

import android.content.Context
import android.location.Geocoder
import com.example.vibestore.data.local.database.VibeStoreRoomDatabase
import com.example.vibestore.data.remote.retrofit.ApiConfig
import com.example.vibestore.data.repository.VibeStoreRepository
import com.example.vibestore.util.SessionPreferences
import com.example.vibestore.util.dataStore
import com.google.android.gms.location.LocationServices
import java.util.Locale

object Injection {
    fun provideRepository(context: Context): VibeStoreRepository {
        val user = SessionPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        val cartDao = VibeStoreRoomDatabase.getDatabase(context).cartDao()
        val favoriteDao = VibeStoreRoomDatabase.getDatabase(context).favouriteDao()
        val orderDao = VibeStoreRoomDatabase.getDatabase(context).orderDao()
        val checkoutDao = VibeStoreRoomDatabase.getDatabase(context).checkoutDao()
        val notificationDao = VibeStoreRoomDatabase.getDatabase(context).notificationDao()
        val userLocationDao = VibeStoreRoomDatabase.getDatabase(context).userLocationDao()
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        val geocoder = Geocoder(context, Locale.getDefault())
        return VibeStoreRepository.getInstance(
            apiService,
            user,
            cartDao,
            favoriteDao,
            orderDao,
            checkoutDao,
            notificationDao,
            userLocationDao,
            fusedLocationClient,
            geocoder,
        )
    }
}