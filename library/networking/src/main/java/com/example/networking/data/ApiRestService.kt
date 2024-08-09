package com.example.networking.data

import retrofit2.Converter
import retrofit2.Retrofit

internal class ApiRestService(
    private val restConfiguration: RestConfiguration,
    private val apiRestClient: ApiRestClient,
    private val converterFactory: Converter.Factory
) : RestService {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(restConfiguration.hostUrl)
            .addConverterFactory(converterFactory)
            .client(apiRestClient.getBuilder().build())
            .build()
    }

    override fun <T> service(clazz: Class<T>): T = retrofit.create(clazz)
}