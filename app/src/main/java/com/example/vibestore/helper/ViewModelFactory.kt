package com.example.vibestore.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vibestore.di.Injection
import com.example.vibestore.repository.ProductRepository
import com.example.vibestore.ui.screen.detail.DetailViewModel
import com.example.vibestore.ui.screen.foryou.ForYouProductViewModel
import com.example.vibestore.ui.screen.product.all.AllProductViewModel
import com.example.vibestore.ui.screen.product.electronic.ElectronicProductViewModel
import com.example.vibestore.ui.screen.product.jewelery.JeweleryProductViewModel
import com.example.vibestore.ui.screen.product.men.MenProductViewModel
import com.example.vibestore.ui.screen.product.women.WomenProductViewModel

@Suppress("UNCHECKED_CAST", "UNREACHABLE_CODE")
class ViewModelFactory(
    private val limit: Int = 20,
    private val id: Int = 1,
    private val repository: ProductRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AllProductViewModel::class.java)){
            AllProductViewModel(repository, limit) as T
        } else if (modelClass.isAssignableFrom(MenProductViewModel::class.java)) {
            MenProductViewModel(repository, limit) as T
        } else if (modelClass.isAssignableFrom(WomenProductViewModel::class.java)){
            WomenProductViewModel(repository, limit) as T
        } else if (modelClass.isAssignableFrom(ElectronicProductViewModel::class.java)) {
            ElectronicProductViewModel(repository, limit) as T
        } else if (modelClass.isAssignableFrom(JeweleryProductViewModel::class.java)){
            JeweleryProductViewModel(repository, limit) as T
        } else if (modelClass.isAssignableFrom(ForYouProductViewModel::class.java)){
            ForYouProductViewModel(repository, limit) as T
        } else {
            DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        fun getInstance(
            context: Context,
            limit: Int = 20,
            id: Int = 1
        ) = ViewModelFactory(
            limit,
            id,
            Injection.provideRepository(context)
        )
    }
}