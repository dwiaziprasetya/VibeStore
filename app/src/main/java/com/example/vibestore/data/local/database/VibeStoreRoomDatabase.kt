package com.example.vibestore.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.vibestore.data.local.dao.CartDao
import com.example.vibestore.data.local.dao.FavouriteDao
import com.example.vibestore.data.local.dao.OrderDao
import com.example.vibestore.data.local.dao.UserLocationDao
import com.example.vibestore.data.local.entity.Cart
import com.example.vibestore.data.local.entity.Favourite
import com.example.vibestore.data.local.entity.Order
import com.example.vibestore.data.local.entity.UserLocation

@Database(
    entities = [
        Cart::class,
        Favourite::class,
        Order::class,
        UserLocation::class,
    ],
    version = 4
)
@TypeConverters(Converter::class)
abstract class VibeStoreRoomDatabase : RoomDatabase(){
    abstract fun cartDao(): CartDao
    abstract fun favouriteDao(): FavouriteDao
    abstract fun orderDao(): OrderDao
    abstract fun userLocationDao(): UserLocationDao

    companion object {
        @Volatile
        private var INSTANCE: VibeStoreRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): VibeStoreRoomDatabase {
            if (INSTANCE == null) {
                synchronized(VibeStoreRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        VibeStoreRoomDatabase::class.java, "cart_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as VibeStoreRoomDatabase
        }
    }
}