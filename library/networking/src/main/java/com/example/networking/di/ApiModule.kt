package com.example.networking.di

import com.example.networking.data.RestService
import com.example.networking.data.api.GithubTrendingProjectsApi
import com.example.networking.data.getService
import com.example.networking.di.NetworkingModule.GithubTopicsApiRestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @Singleton
    fun providesGithubTrendingProjectsApi(
        @GithubTopicsApiRestService restService: RestService
    ): GithubTrendingProjectsApi = restService.getService()

}