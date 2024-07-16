package com.example.vibestore.ui.screen.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.vibestore.repository.VibeStoreRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: VibeStoreRepository
) : ViewModel() {
    fun getUsername() : LiveData<String> {
        return repository.getUsername().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}