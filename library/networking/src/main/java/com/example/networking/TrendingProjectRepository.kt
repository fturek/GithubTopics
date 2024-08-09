package com.example.networking

import com.example.networking.domain.model.TrendingProject

interface TrendingProjectRepository {
    suspend fun getTrendingProjects(): List<TrendingProject>
    suspend fun getTrendingProjectDetails(trendingProjectId: Int): TrendingProject
    suspend fun addTrendingProjectToFavourites(trendingProjectId: Int)
    suspend fun removeTrendingProjectFromFavourites(trendingProjectId: Int)
}