package com.example.vibestore.ui.screen.mycart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.data.local.entity.Cart
import com.example.vibestore.data.repository.VibeStoreRepository
import kotlinx.coroutines.launch

class MyCartViewModel(
    private val repository: VibeStoreRepository
) : ViewModel() {
    val cartItems: LiveData<List<Cart>> = repository.getAllCartItems()
    val totalPrice: LiveData<Double> = repository.calculateTotalPrice()

    fun updateQuantity(cart: Cart, quantity: Int) {
        viewModelScope.launch {
            if (quantity > 0) {
                repository.updateCartById(cart.id, quantity)
            } else {
                repository.deleteCartById(cart.id)
            }
        }
    }
}