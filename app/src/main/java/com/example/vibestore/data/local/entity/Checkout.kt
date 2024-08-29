package com.example.vibestore.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Checkout(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "receiver_name")
    val receiverName: String,

    @ColumnInfo(name = "receiver_address")
    val receiverAddress: String,

    @ColumnInfo(name = "order_items")
    val orderItems: List<Cart>,

    @ColumnInfo(name = "shipping_method")
    val shippingMethod: String,

    @ColumnInfo(name = "checkout_date")
    val checkoutDate: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "shipping_cost")
    val shippingCost: Double,

    @ColumnInfo(name = "shipping_description")
    val shippingDescription: String,

    @ColumnInfo(name = "payment_method")
    val paymentMethod: String = "",

    @ColumnInfo(name = "total_price")
    val totalPrice: Double,
) : Parcelable