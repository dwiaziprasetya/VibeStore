package com.example.vibestore.ui.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.repository.ProductRepository
import com.example.vibestore.ui.common.UiState
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: ProductRepository,
): ViewModel(){

    private val _uiState: MutableLiveData<UiState<ProductResponseItem>> = MutableLiveData(UiState.Loading)
    val uiState: LiveData<UiState<ProductResponseItem>> get() =  _uiState

    fun getSingleProduct(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val product = repository.getSingleProduct(id)
                _uiState.value = UiState.Success(product)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}