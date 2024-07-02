package com.example.vibestore.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.repository.ProductRepository
import com.example.vibestore.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: ProductRepository,
): ViewModel(){

    private val _uiState: MutableStateFlow<UiState<ProductResponseItem>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<ProductResponseItem>> get() =  _uiState

//    private val _product = MutableLiveData<ProductResponseItem>()
//    val product: LiveData<ProductResponseItem> = _product

//    init {
//        getSingleProduct(id)
//    }

    fun getSingleProduct(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getSingleProduct(id))
//            try {
//                val product = repository.getSingleProduct(id)
//                _product.value = product
//            } catch (_: Exception) {
//
//            }
        }
    }
}