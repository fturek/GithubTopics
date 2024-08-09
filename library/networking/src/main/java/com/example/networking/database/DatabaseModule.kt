package com.example.networking.database

import android.content.Context
import com.example.networking.database.favouritetrendingproject.dao.FavouriteTrendingProjectsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideSearchStateDao(database: AppDatabase): FavouriteTrendingProjectsDao =
        database.getFavouriteTrendingProjectsDao()
}