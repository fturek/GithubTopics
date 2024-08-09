package com.example.networking.data.datasource

import com.example.networking.domain.model.TrendingProject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingProjectsCacheDataSource @Inject constructor() {

    private val cache: MutableList<TrendingProject> = mutableListOf()

    fun getTrendingProjects(): List<TrendingProject> = cache

    fun cache(trendingProjects: List<TrendingProject>) {
        cache.clear()
        cache.addAll(trendingProjects.toMutableList())
    }
}