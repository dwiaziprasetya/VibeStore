package com.example.vibestore.ui.screen.registration.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.repository.VibeStoreRepository
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val repository: VibeStoreRepository
) : ViewModel() {
    fun saveLoginData(username: String, token: String) {
        viewModelScope.launch {
            repository.saveLoginData(username, token)
        }
    }
}