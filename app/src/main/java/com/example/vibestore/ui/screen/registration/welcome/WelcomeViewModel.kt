package com.example.vibestore.ui.screen.registration.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.data.local.entity.Notification
import com.example.vibestore.data.repository.VibeStoreRepository
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val repository: VibeStoreRepository
) : ViewModel() {
    fun saveLoginData(username: String, token: String) {
        viewModelScope.launch {
            repository.saveLoginData(username, token)
        }
    }

    fun addNotification(notification: Notification) {
        viewModelScope.launch {
            repository.addNotification(notification)
        }
    }
}