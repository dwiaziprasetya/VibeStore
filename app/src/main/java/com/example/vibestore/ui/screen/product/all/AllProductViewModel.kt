package com.example.vibestore.ui.screen.product.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.repository.ProductRepository
import kotlinx.coroutines.launch

class AllProductViewModel(
    private val repository: ProductRepository,
    limit: Int
): ViewModel() {

    private val _products = MutableLiveData<List<ProductResponseItem>>()
    val products: LiveData<List<ProductResponseItem>> = _products


    init {
        getAllProduct(limit)
    }

    private fun getAllProduct(limit: Int){
        viewModelScope.launch {
            try {
                val product = repository.getAllProducts(limit)
                _products.value = product
            } catch (_: Exception) {

            }
        }
    }

}