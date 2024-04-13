package com.example.vibestore.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vibestore.ui.screen.foryou.ForYouProductViewModel
import com.example.vibestore.ui.screen.product.all.AllProductViewModel
import com.example.vibestore.ui.screen.product.electronic.ElectronicProductViewModel
import com.example.vibestore.ui.screen.product.jewelery.JeweleryProductViewModel
import com.example.vibestore.ui.screen.product.men.MenProductViewModel
import com.example.vibestore.ui.screen.product.women.WomenProductViewModel

@Suppress("UNCHECKED_CAST", "UNREACHABLE_CODE")
class ViewModelFactory(private val limit: Int) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AllProductViewModel::class.java)){
            AllProductViewModel(limit) as T
        } else if (modelClass.isAssignableFrom(MenProductViewModel::class.java)) {
            MenProductViewModel(limit) as T
        } else if (modelClass.isAssignableFrom(WomenProductViewModel::class.java)){
            WomenProductViewModel(limit) as T
        } else if (modelClass.isAssignableFrom(ElectronicProductViewModel::class.java)) {
            ElectronicProductViewModel(limit) as T
        } else if (modelClass.isAssignableFrom(JeweleryProductViewModel::class.java)){
            JeweleryProductViewModel(limit) as T
        } else {
            ForYouProductViewModel(limit) as T
        }
        throw IllegalArgumentException("")
    }
}