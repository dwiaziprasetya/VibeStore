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


}