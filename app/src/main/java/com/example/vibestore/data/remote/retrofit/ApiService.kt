package com.example.vibestore.data.remote.retrofit

import android.telecom.Call
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    fun getAllProduct(): Call
}