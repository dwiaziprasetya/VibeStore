package com.example.vibestore.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vibestore.ui.screen.product.ProductViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val limit: Int) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)){
            return ProductViewModel(limit) as T
        }
        throw IllegalArgumentException("")
    }
}