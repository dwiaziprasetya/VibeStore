package com.example.vibestore.ui.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.repository.ProductRepository
import kotlinx.coroutines.launch

class DetailViewModel(id: Int): ViewModel(){
    private val repository = ProductRepository()

    private val _product = MutableLiveData<ProductResponseItem>()
    val product: LiveData<ProductResponseItem> = _product

    init {
        getSingleProduct(id)
    }

    private fun getSingleProduct(id: Int) {
        viewModelScope.launch {
            try {
                val product = repository.getSingleProduct(id)
                _product.value = product
            } catch (_: Exception) {

            }
        }
    }
}