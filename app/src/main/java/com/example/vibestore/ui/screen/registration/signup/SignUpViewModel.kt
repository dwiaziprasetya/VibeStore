package com.example.vibestore.ui.screen.registration.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.model.UserResponse
import com.example.vibestore.repository.ProductRepository
import com.example.vibestore.ui.common.UiState
import kotlinx.coroutines.launch

class SignUpViewModel (
    private val repository: ProductRepository
): ViewModel() {

    private val _uiState: MutableLiveData<UiState<UserResponse>> = MutableLiveData(null)
    val uiState: LiveData<UiState<UserResponse>> get() = _uiState

    fun register(
        username: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val user = repository.register(username, email, password)
                _uiState.value = UiState.Success(user)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}