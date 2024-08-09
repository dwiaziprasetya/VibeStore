package com.example.vibestore.ui.screen.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.vibestore.data.local.entity.Favourite
import com.example.vibestore.data.repository.VibeStoreRepository

class FavouriteViewModel(
    private val repository: VibeStoreRepository
) : ViewModel() {
    val allFavorites: LiveData<List<Favourite>> = repository.getAllFavourites()
}