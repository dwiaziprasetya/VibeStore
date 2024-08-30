package com.example.vibestore.ui.screen.payment.success_payment

import androidx.lifecycle.ViewModel
import com.example.vibestore.data.repository.VibeStoreRepository

class SuccessPaymentViewModel(
    private val repository: VibeStoreRepository
) : ViewModel() {
    val latestCheckout = repository.getLatestCheckout()
}