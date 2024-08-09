package com.example.networking.di

import com.example.networking.TrendingProjectRepository
import com.example.networking.data.repository.TrendingProjectRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindTrendingProjectRepository(impl: TrendingProjectRepositoryImpl): TrendingProjectRepository
}