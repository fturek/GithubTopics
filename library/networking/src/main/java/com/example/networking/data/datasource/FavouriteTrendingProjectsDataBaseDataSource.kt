package com.example.networking.data.datasource

import com.example.networking.data.mapper.toEntity
import com.example.networking.database.favouritetrendingproject.dao.FavouriteTrendingProjectsDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavouriteTrendingProjectsDataBaseDataSource @Inject constructor(
    private val dao: FavouriteTrendingProjectsDao
) {
    suspend fun getFavouriteTrendingProjectsIds(): List<Int> =
        dao.getFavouriteTrendingProjectsId().map { it.trendingProjectId }

    suspend fun setFavouriteTrendingProject(trendingProjectId: Int) =
        dao.insert(toEntity(trendingProjectId))

    suspend fun removeFavouriteTrendingProject(trendingProjectId: Int) =
        dao.delete(toEntity(trendingProjectId))
}