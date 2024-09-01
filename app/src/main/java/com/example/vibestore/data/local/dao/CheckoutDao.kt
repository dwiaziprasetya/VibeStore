package com.example.vibestore.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vibestore.data.local.entity.Checkout

@Dao
interface CheckoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(checkout: Checkout)

    @Query("SELECT * FROM checkout")
    fun getAllCheckouts(): LiveData<List<Checkout>>

    @Query("SELECT * FROM checkout ORDER BY checkout_date DESC LIMIT 1")
    fun getLatestCheckout(): LiveData<Checkout>

    @Query("UPDATE checkout SET payment_method = :paymentMethod WHERE id = :id")
    suspend fun updatePaymentMethod(id: Int, paymentMethod: String)

    @Query("DELETE FROM checkout")
    suspend fun deleteAllitems()
}