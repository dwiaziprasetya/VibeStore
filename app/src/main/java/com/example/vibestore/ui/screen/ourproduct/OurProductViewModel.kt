package com.example.vibestore.ui.screen.ourproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.repository.VibeStoreRepository
import com.example.vibestore.ui.common.UiState
import kotlinx.coroutines.launch

class OurProductViewModel(
    private val repository: VibeStoreRepository
): ViewModel() {
    private val _uiState: MutableLiveData<UiState<List<ProductResponseItem>>> = MutableLiveData(UiState.Loading)
    val uiState: LiveData<UiState<List<ProductResponseItem>>> get() =  _uiState

    fun getAllProduct(limit: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val product = repository.getAllProducts(limit)
                _uiState.value = UiState.Success(product)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun sortProduct(sort: String) {
        viewModelScope.launch {
            try {
                val product = repository.sortProduct(sort)
                _uiState.value = UiState.Success(product)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun searchProduct(query: String) {
        viewModelScope.launch {
            try {
                val product = repository.searchProduct(query)
                if (product.isEmpty()) {
                    _uiState.value = UiState.Error("Product not found")
                } else {
                    _uiState.value = UiState.Success(product)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}