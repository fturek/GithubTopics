package com.example.networking.data

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class ApiRestClient {

    fun getBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
        .apply {
            addTimeouts()
        }

    private fun OkHttpClient.Builder.addTimeouts() {
        connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
    }

    companion object {
        private const val TIMEOUT_SECONDS = 120L
    }
}