package com.example.vibestore.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vibestore.di.Injection
import com.example.vibestore.repository.ProductRepository
import com.example.vibestore.ui.screen.detail.DetailViewModel
import com.example.vibestore.ui.screen.foryou.ForYouViewModel
import com.example.vibestore.ui.screen.product.all.AllProductViewModel
import com.example.vibestore.ui.screen.product.electronic.ElectronicProductViewModel
import com.example.vibestore.ui.screen.product.jewelery.JeweleryProductViewModel
import com.example.vibestore.ui.screen.product.men.MenProductViewModel
import com.example.vibestore.ui.screen.product.women.WomenProductViewModel
import com.example.vibestore.ui.screen.registration.signup.SignUpViewModel

@Suppress("UNCHECKED_CAST", "UNREACHABLE_CODE")
class ViewModelFactory(
    private val limit: Int = 20,
    private val repository: ProductRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AllProductViewModel::class.java)){
            AllProductViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(MenProductViewModel::class.java)) {
            MenProductViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(WomenProductViewModel::class.java)){
            WomenProductViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ElectronicProductViewModel::class.java)) {
            ElectronicProductViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(JeweleryProductViewModel::class.java)){
            JeweleryProductViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ForYouViewModel::class.java)){
            ForYouViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            DetailViewModel(repository) as T
        } else {
            SignUpViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        fun getInstance(
            context: Context,
            limit: Int = 20,
        ) = ViewModelFactory(
            limit,
            Injection.provideRepository(context)
        )
    }
}