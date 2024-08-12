package com.example.vibestore.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vibestore.data.local.entity.Order

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: Order)

    @Query("SELECT * FROM `order` ORDER BY order_date DESC")
    fun getAllOrders(): LiveData<List<Order>>

    @Query("DELETE FROM `order` WHERE id = :orderId")
    suspend fun deleteOrderById(orderId: Int)

    @Query("DELETE FROM `order`")
    suspend fun deleteAllOrders()

}