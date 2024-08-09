package com.example.vibestore.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vibestore.data.local.entity.Favourite

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: Favourite)

    @Query("SELECT * FROM favourite ORDER BY id DESC")
    fun getAllFavourites(): LiveData<List<Favourite>>

    @Query("DELETE FROM favourite WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT EXISTS (SELECT 1 FROM favourite WHERE product_id = :productId)")
    fun isProductFavorited(productId: Int): LiveData<Boolean>

    @Query("DELETE FROM favourite")
    suspend fun deleteAllItems()
}