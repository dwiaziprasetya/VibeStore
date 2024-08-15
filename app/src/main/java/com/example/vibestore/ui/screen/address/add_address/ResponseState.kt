package com.example.vibestore.ui.screen.address.add_address

sealed class ResponseState<out T> {
    object Idle : ResponseState<Nothing>()
    object Loading : ResponseState<Nothing>()
    data class Error(val error: Throwable) : ResponseState<Nothing>()
    data class Success<R>(val data: R) : ResponseState<R>()
}