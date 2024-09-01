package com.example.vibestore.ui.screen.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.data.repository.VibeStoreRepository
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val repository: VibeStoreRepository
) : ViewModel() {
    val notifications = repository.getAllNotifications()

    fun markAsRead(notificationId: Int) {
        viewModelScope.launch {
            repository.markAsRead(notificationId)
        }
    }
}