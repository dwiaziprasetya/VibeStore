package com.example.vibestore.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Notification(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "notificationType")
    val notificationType: String,

    @ColumnInfo(name = "firstProductName")
    val firstProductName: String = "",

    @ColumnInfo(name = "quantityCheckout")
    val quantityCheckout: Int = 0,

    @ColumnInfo(name = "firstProductImage")
    val firstProductImage: String = "",

    @ColumnInfo(name = "message")
    val message: String,

    @ColumnInfo(name = "messageDetail")
    val messageDetail: String = "",

    @ColumnInfo(name = "date")
    val date: String = getCurrentFormattedDate(),

    @ColumnInfo(name = "isRead")
    val isRead: Boolean = false
) : Parcelable