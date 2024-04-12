package com.example.vibestore.ui.screen.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(limit: Int): ViewModel() {
    private val repository = ProductRepository()


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

//    fun getProductByCategory(category: String){
//        viewModelScope.launch {
//            try {
//                val product = repository.getProductByCategory(category)
//                _product.value = product
//            } catch (_: Exception) {
//
//            }
//        }
//    }
}