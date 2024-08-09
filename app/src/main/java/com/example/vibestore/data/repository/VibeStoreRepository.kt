package com.example.vibestore.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.vibestore.data.local.dao.CartDao
import com.example.vibestore.data.local.dao.FavouriteDao
import com.example.vibestore.data.local.entity.Cart
import com.example.vibestore.data.local.entity.Favourite
import com.example.vibestore.data.remote.retrofit.ApiService
import com.example.vibestore.model.LoginResponse
import com.example.vibestore.model.ProductResponseItem
import com.example.vibestore.model.UserResponse
import com.example.vibestore.util.SessionPreferences
import kotlinx.coroutines.flow.Flow

class VibeStoreRepository private constructor(
    private val apiService : ApiService,
    private val pref: SessionPreferences,
    private val cartDao: CartDao,
    private val favouriteDao: FavouriteDao
){
    fun getSession() : Flow<LoginResponse> {
        return pref.getSession()
    }

    fun isProductFavorited(productId: Int): LiveData<Boolean> {
        return favouriteDao.isProductFavorited(productId)
    }

    suspend fun addToFavourite(favourite: Favourite) {
        favouriteDao.insert(favourite)
    }

    fun getAllFavourites(): LiveData<List<Favourite>> {
        return favouriteDao.getAllFavourites()
    }

    suspend fun deleteFavouriteById(id: Int) {
        favouriteDao.deleteById(id)
    }

    suspend fun updateCartById(cartId: Int, quantity: Int) {
        cartDao.updateQuantity(cartId, quantity)
    }

    suspend fun deleteCartById(cartId: Int) {
        cartDao.deleteById(cartId)
    }

    fun calculateTotalPrice() : LiveData<Double> {
        return cartDao.getAllCart().map { cartItems ->
            cartItems.sumOf {
                it.productPrice.toDouble() * it.productQuantity
            }
        }
    }

    fun getAllCartItems() : LiveData<List<Cart>> {
        return cartDao.getAllCart()
    }

    suspend fun addToCart(cart: Cart) {
        val existingCartItem = cartDao.getCartItemByProductId(cart.productId)
        if (existingCartItem != null){
            val newQuantity = existingCartItem.productQuantity + cart.productQuantity
            cartDao.updateQuantity(existingCartItem.id, newQuantity)
        } else {
            cartDao.insert(cart)
        }
    }

    fun getUsername() : Flow<String> {
        return pref.getUsername()
    }

    suspend fun saveLoginData(username: String, token: String) {
        pref.saveLoginData(username, token)
    }

    suspend fun logout() {
        pref.logout()
        cartDao.deleteAllItems()
        favouriteDao.deleteAllItems()
    }

    suspend fun login(
        username: String,
        password: String
    ): LoginResponse {
        return apiService.login(username, password)
    }

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): UserResponse {
        return apiService.register(username, email, password)
    }

    suspend fun getAllProducts(limit: Int): List<ProductResponseItem> {
        return apiService.getAllProduct(limit)
    }

    suspend fun getProductByCategory(
        category: String,
        limit: Int
    ): List<ProductResponseItem> {
        return apiService.getProductByCategory(category, limit)
    }

    suspend fun getSingleProduct(id: Int): ProductResponseItem {
        return apiService.getSingleProduct(id)
    }

    suspend fun sortProduct(sort: String): List<ProductResponseItem> {
        return apiService.sortProduct(sort)
    }

    suspend fun searchProduct(query: String): List<ProductResponseItem> {
        val allproduct = apiService.getAllProduct(Int.MAX_VALUE)
        return allproduct.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    companion object {
        fun getInstance(
            apiService: ApiService,
            userPreferences: SessionPreferences,
            cartDao: CartDao,
            favouriteDao: FavouriteDao
        ) = VibeStoreRepository(
            apiService,
            userPreferences,
            cartDao,
            favouriteDao
        )
    }
}