package com.example.vibestore.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vibestore.data.local.entity.Cart

@Dao
interface CartDao {
    @Query("SELECT * FROM cart WHERE product_id = :productId LIMIT 1")
    suspend fun getCartItemByProductId(productId: Int): Cart?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cart: Cart)

    @Query("SELECT * FROM cart ORDER BY id DESC")
    fun getAllCart(): LiveData<List<Cart>>

    @Query("UPDATE cart SET product_quantity = :quantity WHERE id = :cartId")
    suspend fun updateQuantity(cartId: Int, quantity: Int)

    @Query("DELETE FROM cart WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM cart")
    suspend fun deleteAllItems()
}