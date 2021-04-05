package com.example.moviews.data

interface OnLoadDataCallback<T> {
    fun onSuccess(data: T)
    fun onError(e: Exception?)
}
