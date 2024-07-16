package com.example.vibestore.ui.screen.registration.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.model.LoginResponse
import com.example.vibestore.repository.VibeStoreRepository
import com.example.vibestore.ui.common.UiState
import kotlinx.coroutines.launch

class LoginViewModel (
    private val repository: VibeStoreRepository
) : ViewModel() {
    private val _uiState: MutableLiveData<UiState<LoginResponse>?> = MutableLiveData(null)
    val uiState: LiveData<UiState<LoginResponse>?> get() = _uiState

    fun login(
        username: String,
        password: String
    ) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val user = repository.login(username, password)
                _uiState.value = UiState.Success(user)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun saveLoginData(username: String, token: String) {
        viewModelScope.launch {
            repository.saveLoginData(username, token)
        }
    }

    fun resetUiState() {
        _uiState.value = null
    }
}