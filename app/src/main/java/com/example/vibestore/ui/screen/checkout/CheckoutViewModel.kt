package com.example.vibestore.ui.screen.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vibestore.data.local.entity.Order
import com.example.vibestore.data.local.entity.UserLocation
import com.example.vibestore.data.repository.VibeStoreRepository

class CheckoutViewModel(
    private val repository: VibeStoreRepository
) : ViewModel() {
    val orderItems: LiveData<Order> = repository.getLatestOrder()

    private val _selectedShippingId = MutableLiveData<Int?>()
    val selectedShippingId: LiveData<Int?> get() = _selectedShippingId

    private val _selectedPaymentId = MutableLiveData<Int?>()
    val selectedPaymentId: LiveData<Int?> get() = _selectedPaymentId

    fun selectPayment(id: Int) {
        _selectedPaymentId.value = id
    }

    fun selectShipping(id: Int) {
        _selectedShippingId.value = id
    }

    fun getUserLocationById(id: Int): LiveData<UserLocation> {
        return repository.getUserLocationById(id)
    }
}