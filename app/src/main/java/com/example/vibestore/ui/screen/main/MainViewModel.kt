package com.example.vibestore.ui.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.vibestore.data.repository.VibeStoreRepository
import com.example.vibestore.model.LoginResponse

class MainViewModel (
    private val repository: VibeStoreRepository
) : ViewModel() {
    fun getSession() : LiveData<LoginResponse> {
        return repository.getSession().asLiveData()
    }
}