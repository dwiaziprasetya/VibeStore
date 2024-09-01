package com.example.vibestore.ui.screen.yourorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.vibestore.data.local.entity.Checkout
import com.example.vibestore.data.repository.VibeStoreRepository

class YourOrderViewModel(
    private val repository: VibeStoreRepository
) : ViewModel() {

    val checkoutItems: LiveData<List<Checkout>> = repository.getAllCheckout()


}