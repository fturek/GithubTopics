package com.example.networking.data

interface RestService {
    fun <T> service(clazz: Class<T>): T
}

inline fun <reified T> RestService.getService(): T = service(T::class.java)