package com.example.vibestore.ui.screen.ourproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.repository.ProductRepository
import kotlinx.coroutines.launch

class OurProductViewModel: ViewModel() {
    private val repository = ProductRepository()

    private val _product = MutableLiveData<List<ProductResponseItem>>()
    val product: LiveData<List<ProductResponseItem>> = _product


    init {
        getAllProduct()
    }

    private fun getAllProduct(){
        viewModelScope.launch {
            try {
                val product = repository.getAllProducts(20)
                _product.value = product
            } catch (_: Exception) {

            }
        }
    }
}