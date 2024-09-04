package com.example.vibestore.ui.screen.mycart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.vibestore.data.local.entity.Cart
import com.example.vibestore.data.local.entity.Order
import com.example.vibestore.data.repository.VibeStoreRepository
import kotlinx.coroutines.launch

class MyCartViewModel(
    private val repository: VibeStoreRepository
) : ViewModel() {
    val cartItems: LiveData<List<Cart>> = repository.getAllCartItems()
    private val _selectedCartItems = MutableLiveData<Set<Cart>>(emptySet())
    val selectedCartItems: LiveData<Set<Cart>> = _selectedCartItems

    val totalPrice: LiveData<Double> = _selectedCartItems.map { items ->
        items.sumOf { it.productPrice.toDouble() * it.productQuantity }
    }

    fun updateQuantity(cart: Cart, quantity: Int) {
        viewModelScope.launch {
            if (quantity > 0) {
                repository.updateCartById(cart.id, quantity)
            } else {
                repository.deleteCartById(cart.id)
            }

            val currentItems = _selectedCartItems.value.orEmpty().toMutableSet()
            if (currentItems.contains(cart)) {
                val updatedCart = cart.copy(productQuantity = quantity)
                currentItems.remove(cart)
                if (quantity > 0) currentItems.add(updatedCart)
                _selectedCartItems.value = currentItems
            }
        }
    }

    fun createOrderFromSelectedItems() {
        val selectedItems = _selectedCartItems.value.orEmpty()
        if (selectedItems.isNotEmpty()) {
            val totalPrice = selectedItems.sumOf { it.productPrice.toDouble() * it.productQuantity }
            val order = Order(
                totalPrice = totalPrice,
                items = selectedItems.toList()
            )
            viewModelScope.launch {
                repository.addOrder(order)
                selectedItems.forEach { cartItem ->
                    repository.deleteCartById(cartItem.id)
                }

                _selectedCartItems.value = emptySet()
            }
        }
    }

    fun updateCheckedItem(cart: Cart, isChecked: Boolean) {
        val currentItems = _selectedCartItems.value.orEmpty().toMutableSet()
        if (isChecked) {
            currentItems.add(cart)
        } else {
            currentItems.remove(cart)
        }
        _selectedCartItems.value = currentItems
    }
}