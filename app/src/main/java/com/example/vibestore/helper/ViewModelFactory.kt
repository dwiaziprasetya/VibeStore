package com.example.vibestore.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vibestore.di.Injection
import com.example.vibestore.repository.VibeStoreRepository
import com.example.vibestore.ui.screen.detail.DetailViewModel
import com.example.vibestore.ui.screen.foryou.ForYouViewModel
import com.example.vibestore.ui.screen.main.MainViewModel
import com.example.vibestore.ui.screen.ourproduct.OurProductViewModel
import com.example.vibestore.ui.screen.product.all.AllProductViewModel
import com.example.vibestore.ui.screen.product.electronic.ElectronicProductViewModel
import com.example.vibestore.ui.screen.product.jewelery.JeweleryProductViewModel
import com.example.vibestore.ui.screen.product.men.MenProductViewModel
import com.example.vibestore.ui.screen.product.women.WomenProductViewModel
import com.example.vibestore.ui.screen.profile.ProfileViewModel
import com.example.vibestore.ui.screen.registration.login.LoginViewModel
import com.example.vibestore.ui.screen.registration.signup.SignUpViewModel
import com.example.vibestore.ui.screen.registration.welcome.WelcomeViewModel

@Suppress("UNCHECKED_CAST", "UNREACHABLE_CODE")
class ViewModelFactory(
    private val limit: Int = 20,
    private val repository: VibeStoreRepository,
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
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(OurProductViewModel::class.java)) {
            OurProductViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(SignUpViewModel::class.java)){
            SignUpViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            LoginViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            ProfileViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(repository) as T
        } else {
            WelcomeViewModel(repository) as T
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