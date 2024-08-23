package com.example.vibestore.ui.screen.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vibestore.data.local.entity.UserLocation
import com.example.vibestore.data.repository.VibeStoreRepository
import kotlinx.coroutines.launch

class AddressViewModel(
    private val repository: VibeStoreRepository
) : ViewModel() {
    val allUserLocations: LiveData<List<UserLocation>> = repository.getAllUsersLocation()

    private val _selectedItemId = MutableLiveData<Int?>()
    val selectedItemId: LiveData<Int?> get() = _selectedItemId

    fun selectItem(id: Int) {
        _selectedItemId.value = id
    }

    fun deleteItem(id: Int) {
        viewModelScope.launch {
            repository.deleteUsersLocation(id)
        }
    }
}