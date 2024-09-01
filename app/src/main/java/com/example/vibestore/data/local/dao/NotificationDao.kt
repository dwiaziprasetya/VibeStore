package com.example.vibestore.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vibestore.data.local.entity.Notification

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: Notification)

    @Query("SELECT * FROM notification ORDER BY id DESC")
    fun getAllNotifications(): LiveData<List<Notification>>

    @Query("UPDATE notification SET isRead = 1 WHERE id = :notificationId")
    suspend fun markAsRead(notificationId: Int)

    @Query("DELETE FROM notification")
    suspend fun deleteAllNotifications()

    @Query("SELECT COUNT(*) FROM notification WHERE isRead = 0")
    fun getUnreadNotificationCount(): LiveData<Int>
}