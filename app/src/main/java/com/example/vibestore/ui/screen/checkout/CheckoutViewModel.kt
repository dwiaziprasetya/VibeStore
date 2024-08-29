package com.example.vibestore.ui.screen.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.data.local.entity.Cart
import com.example.vibestore.data.local.entity.Checkout
import com.example.vibestore.data.local.entity.Order
import com.example.vibestore.data.local.entity.UserLocation
import com.example.vibestore.data.repository.VibeStoreRepository
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val repository: VibeStoreRepository
) : ViewModel() {
    val orderItems: LiveData<Order> = repository.getLatestOrder()

    private val _selectedShippingId = MutableLiveData<Int?>()
    val selectedShippingId: LiveData<Int?> get() = _selectedShippingId

    private val _selectedCouponId = MutableLiveData<Int?>()
    val selectedCouponId: LiveData<Int?> get() = _selectedCouponId

    fun selectCoupon(id: Int) {
        _selectedCouponId.value = id
    }

    fun addToCheckout(
        receiverName: String,
        receiverAddress: String,
        orderItems: List<Cart>,
        shippingMethod: String,
        shippingCost: Double,
        shippingDescription: String,
        paymentMethod: String = "",
        totalPrice: Double
    ) {
        val checkout = Checkout(
            receiverName = receiverName,
            receiverAddress = receiverAddress,
            orderItems = orderItems,
            shippingMethod = shippingMethod,
            shippingCost = shippingCost,
            shippingDescription = shippingDescription,
            paymentMethod = paymentMethod,
            totalPrice = totalPrice
        )

        viewModelScope.launch {
            repository.addCheckout(checkout)
        }
    }

    fun selectShipping(id: Int) {
        _selectedShippingId.value = id
    }

    fun getUserLocationById(id: Int): LiveData<UserLocation> {
        return repository.getUserLocationById(id)
    }
}