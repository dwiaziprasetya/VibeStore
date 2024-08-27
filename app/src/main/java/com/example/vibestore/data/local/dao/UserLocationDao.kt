package com.example.vibestore.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vibestore.data.local.entity.UserLocation

@Dao
interface UserLocationDao {
    @Query("SELECT * FROM userlocation")
    fun getAllUserLocations(): LiveData<List<UserLocation>>

    @Query("SELECT * FROM userlocation WHERE id = :id")
    fun getUserLocationById(id: Int): LiveData<UserLocation>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserLocation(userLocation: UserLocation)

    @Query("DELETE FROM userlocation WHERE id = :id")
    suspend fun deleteUserLocation(id: Int)
}