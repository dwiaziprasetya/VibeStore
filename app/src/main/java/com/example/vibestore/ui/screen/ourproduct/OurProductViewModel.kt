package com.example.vibestore.ui.screen.ourproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.repository.ProductRepository

class OurProductViewModel: ViewModel() {
    private val repository = ProductRepository()

    private val _product = MutableLiveData<List<ProductResponseItem>>()
    val product: LiveData<List<ProductResponseItem>> = _product


//    fun getAllProduct(){
//        viewModelScope.launch {
//            try {
//                val product = repository.getAllProduct()
//                _product.value = product
//            } catch (_: Exception) {
//
//            }
//        }
//    }
}