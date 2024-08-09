package com.example.networking.di

import com.example.networking.data.ApiRestClient
import com.example.networking.data.ApiRestService
import com.example.networking.data.RestConfiguration
import com.example.networking.data.RestService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkingModule {

    @Qualifier
    @Singleton
    @Retention(AnnotationRetention.RUNTIME)
    annotation class GithubTopicsApiRestService

    @Provides
    @Singleton
    fun provideApiRestClient(): ApiRestClient = ApiRestClient()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideConverterFactory(
        gson: Gson
    ): Converter.Factory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    @GithubTopicsApiRestService
    fun provideGithubTopicsApiRestService(
        apiRestClient: ApiRestClient,
        converterFactory: Converter.Factory
    ): RestService = ApiRestService(
        restConfiguration = RestConfiguration(
            hostUrl = "https://api.github.com/"
        ),
        apiRestClient = apiRestClient,
        converterFactory = converterFactory
    )
}