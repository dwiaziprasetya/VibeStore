package com.example.vibestore.ui.screen.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.vibestore.data.local.entity.Order
import com.example.vibestore.data.repository.VibeStoreRepository

class CheckoutViewModel(
    private val repository: VibeStoreRepository
) : ViewModel() {
    val orderItems: LiveData<Order> = repository.getLatestOrder()
}