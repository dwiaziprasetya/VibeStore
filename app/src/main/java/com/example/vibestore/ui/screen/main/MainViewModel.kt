package com.example.vibestore.ui.screen.main

import androidx.lifecycle.ViewModel
import com.example.vibestore.model.LoginResponse
import com.example.vibestore.repository.VibeStoreRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel (
    private val repository: VibeStoreRepository
) : ViewModel() {
    fun getSession() : Flow<LoginResponse> {
        return repository.getSession()
    }
}