package com.example.vibestore.ui.screen.product.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.data.repository.VibeStoreRepository
import com.example.vibestore.ui.common.UiState
import kotlinx.coroutines.launch

class AllProductViewModel(
    private val repository: VibeStoreRepository,
): ViewModel() {

    private val _uiState: MutableLiveData<UiState<List<ProductResponseItem>>> = MutableLiveData(UiState.Loading)
    val uiState: LiveData<UiState<List<ProductResponseItem>>> get() =  _uiState

    fun getAllProduct(limit: Int){
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

}