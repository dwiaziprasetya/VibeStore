package com.example.vibestore.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.vibestore.data.local.entity.Cart
import com.example.vibestore.data.repository.VibeStoreRepository

class HomeViewModel(
    private val repository: VibeStoreRepository
) : ViewModel(){
    val cartItems: LiveData<List<Cart>> = repository.getAllCartItems()
    val unreadNotificationCount: LiveData<Int> = repository.getUnReadNotification()
}