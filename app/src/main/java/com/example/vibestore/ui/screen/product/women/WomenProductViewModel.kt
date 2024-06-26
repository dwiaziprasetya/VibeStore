package com.example.vibestore.ui.screen.product.women

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.repository.ProductRepository
import kotlinx.coroutines.launch

class WomenProductViewModel(limit: Int): ViewModel() {
    private val repository = ProductRepository()

    private val _products = MutableLiveData<List<ProductResponseItem>>()
    val products: LiveData<List<ProductResponseItem>> = _products


    init {
        getProductByCategory("women's clothing", limit)
    }


    private fun getProductByCategory(category: String, limit: Int){
        viewModelScope.launch {
            try {
                val product = repository.getProductByCategory(category,limit)
                _products.value = product
            } catch (_: Exception) {

            }
        }
    }
}